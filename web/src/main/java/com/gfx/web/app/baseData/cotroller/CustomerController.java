package com.gfx.web.app.baseData.cotroller;

import com.gfx.web.app.baseData.service.CustomerService;
import com.gfx.web.app.constant.CommonConstant;
import com.gfx.web.base.constant.VMSConstant;
import com.gfx.web.base.context.UserContextHolder;
import com.gfx.web.base.dto.Pagination;
import com.gfx.web.base.dto.UserInfoDto;
import com.gfx.web.base.dto.VMSResponse;
import com.gfx.web.base.dto.VMSResponseFactory;
import com.gfx.web.common.entity.Customer;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author tony
 * @date 2018/9/21
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * 获取客户列表
     *
     * @param pagination 分页数据
     * @return 客户列表
     */
    @GetMapping("/getCustomerList")
    @RequiresAuthentication
    public Map<String, Object> getCustomerList(Pagination pagination, HttpSession session) {
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        String isAdmin = "N";
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        try {
            UserInfoDto userInfo = UserContextHolder.getUserInfo();
            // UserInfoDto userInfo = (UserInfoDto) session.getAttribute(VMSConstant.SessionConstant.USER_INFO);
            String userId = userInfo.getUserId();
            List<String> roles = userInfo.getRoles();
            if (roles.contains(CommonConstant.RoleConstant.ADMIN)) {
                isAdmin = "Y";
            }
            Map<String, Object> customers = customerService.getCustomerList(pagination, userId, isAdmin);
            if (MapUtils.isNotEmpty(customers)) {
                List<Customer> rows = (List<Customer>) customers.get("data");
                vmsResponse.setCustomerInfo("rows", rows);
                vmsResponse.setResponseBodyTotal((Long) customers.get("total"));
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            }
        } catch (Exception e) {
            log.warn("customer list is error ->", e);
        }

        return vmsResponse.generateResponseBody();
    }

    /**
     * 新增客户
     *
     * @param customer 客户
     * @return 响应
     */
    @PostMapping("/addCustomer")
    @RequiresPermissions("customer:add")
    public Map<String, Object> addCustomer(@RequestBody Customer customer) {
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        try {
            customer.setSaleMan(UserContextHolder.getUserInfo().getUserId());
            if (customerService.addCustomer(customer)) ;
            {
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            }
        } catch (Exception e) {
            log.warn("add customer err -->",e);
        }
        return vmsResponse.generateResponseBody();
    }


    /**
     * 通过ajax获取所有客户
     * @return 响应
     */
    @GetMapping("/getCustomerListAjax")
    @RequiresAuthentication
    public Map<String,Object> getCustomerListAjax(){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        List<Customer> list = customerService.getCustomerListAjax();
        if (list.size()>0){
            vmsResponse.setResponseBodyData(list);
            vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
        }
        return vmsResponse.generateResponseBody();
    }
}
