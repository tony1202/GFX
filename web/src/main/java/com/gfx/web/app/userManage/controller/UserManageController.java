package com.gfx.web.app.userManage.controller;

import com.gfx.web.app.userManage.dto.UserDto;
import com.gfx.web.app.userManage.service.UserManageService;
import com.gfx.web.base.dto.Pagination;
import com.gfx.web.base.dto.VMSResponse;
import com.gfx.web.base.dto.VMSResponseFactory;
import com.gfx.web.common.entity.Role;
import com.gfx.web.common.entity.User;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author tony
 * @date 2018/9/28
 */
@RestController
@RequestMapping("/userManage")
public class UserManageController {
    private static final Logger log = LoggerFactory.getLogger(UserManageController.class);

    private UserManageService userManageService;

    @Autowired
    public UserManageController(UserManageService userManageService) {
        this.userManageService = userManageService;
    }

    /**
     * 查询业务员列表
     *
     * @param roleId 角色id
     * @return
     */
    @GetMapping("/getUserByRoleId")
    public Map<String, Object> getUserByRoleId(@RequestParam("roleId") String roleId) {
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        //查询业务员集合
        List<User> list = userManageService.getUserByRoleId(roleId);
        if (list.size() > 0) {
            vmsResponse.setResponseBodyData(list);
            vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
        }
        return vmsResponse.generateResponseBody();
    }

    /**
     * 查询员工列表
     *
     * @return 员工数据
     */
    @GetMapping("/getUserList")
    public Map<String, Object> getUserList(Pagination pagination) {
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        try {
            Map<String, Object> map = userManageService.getUserList(pagination);
            if (MapUtils.isNotEmpty(map)) {
                vmsResponse.setResponseBodyTotal((Long) map.get("total"));
                vmsResponse.setCustomerInfo("rows", (List<UserDto>) map.get("data"));
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            }
        } catch (Exception e) {
            log.warn("getUserList is error -> ", e);
        }
        return vmsResponse.generateResponseBody();
    }

    /**
     * 更新员工信息
     *
     * @param user 员工信息
     * @return
     */
    @PutMapping("/updateUserAdmin")
    @RequiresPermissions("user:update")
    public Map<String, Object> updateUserAdmin(@RequestBody UserDto user) {
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        try {
            if (userManageService.updateUserAdmin(user)) {
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            }
        } catch (Exception e) {
            log.warn("updateUserAdmin is error ->", e);
        }
        return vmsResponse.generateResponseBody();
    }

    /**
     * 删除员工
     *
     * @param userId 员工id
     * @return
     */
    @DeleteMapping("/deleteUserAdmin/{userId}")
    @RequiresPermissions("user:delete")
    public Map<String, Object> deleteUserAdmin(@PathVariable String userId) {
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);

        try {
            boolean b = userManageService.deleteUserAdmin(userId);
            if (b) {
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            }
        } catch (Exception e) {
            log.warn("deleteUserAdmin is error ->", e);
        }

        return vmsResponse.generateResponseBody();
    }

    /**
     * 新增员工
     *
     * @param user 员工信息
     * @return
     */
    @PostMapping("/addUserAdmin")
    @RequiresPermissions("user:add")
    public Map<String, Object> addUserAdmin(@RequestBody UserDto user) {
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        try {
            String userId = userManageService.addUserAdmin(user);
            if (StringUtils.isNotBlank(userId)){
                vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
                String msg = "员工账号: "+userId+"; 初始密码: 123456";
                vmsResponse.setResponseBodyMsg(msg);
            }
        } catch (Exception e) {
            log.warn("addUserAdmin is error ->",e);
        }
        return vmsResponse.generateResponseBody();
    }

    /**
     * getRoleList
     * @return
     */
    @GetMapping("/getRoleList")
    public Map<String,Object> getRoleList(){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        List<Role> list = userManageService.getRoleList();
        if (list.size()>0){
            vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
            vmsResponse.setResponseBodyData(list);
        }
        return vmsResponse.generateResponseBody();
    }
}
