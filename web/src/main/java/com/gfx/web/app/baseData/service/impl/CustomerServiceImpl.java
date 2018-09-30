package com.gfx.web.app.baseData.service.impl;

import com.gfx.web.app.baseData.service.CustomerService;
import com.gfx.web.app.constant.CommonConstant;
import com.gfx.web.base.dto.Pagination;
import com.gfx.web.common.dao.mapper.CustomerMapper;
import com.gfx.web.common.entity.Customer;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tony
 * @date 2018/9/21
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    /**
     * 分页查询客户信息
     *
     * @param pagination 分页条件
     * @param userId     用户id
     * @param isAdmin    是否admin
     * @return 分页数据 key:total - 总条数;key:data - 数据列
     */
    @Override
    public Map<String, Object> getCustomerList(Pagination pagination, String userId, String isAdmin) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        params.put("isAdmin", isAdmin);
        params.put("userId", userId);
        switch (pagination.getSearchType()) {
            case CommonConstant.CustomerConstant.SEARCH_TYPE_ALL:
                break;
            case CommonConstant.CustomerConstant.SEARCH_TYPE_LINK_MAN:
                params.put("linkMan", pagination.getKeyWord());
                break;
            case CommonConstant.CustomerConstant.SEARCH_TYPE_NAME:
                params.put("name", pagination.getKeyWord());
                break;
            default:
                break;
        }
        Page<Customer> page = null;
        if (pagination.getOffset() >= 0 && pagination.getLimit() >= 0) {
            page = PageHelper.startPage(pagination.getOffset(), pagination.getLimit(), true);
        }
        List<Customer> list = customerMapper.getCustomerList(params);
        if (page != null){

            result.put("total", page.getTotal());
        }else {

            result.put("total", (long)list.size());
        }
        result.put("data", list);
        return result;
    }


    /**
     * 新客户
     *
     * @param customer 客户
     * @return 新增结果
     */
    @Override
    public Boolean addCustomer(Customer customer) {

        return customerMapper.insert(customer) == 1;
    }


    /**
     * 通过ajax获取所有客户
     *
     * @return 客户集合
     */
    @Override
    public List<Customer> getCustomerListAjax() {

        return customerMapper.getCustomerListAjax();
    }
}
