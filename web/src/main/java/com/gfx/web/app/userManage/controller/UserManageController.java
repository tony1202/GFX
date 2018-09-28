package com.gfx.web.app.userManage.controller;

import com.gfx.web.app.userManage.service.UserManageService;
import com.gfx.web.base.dto.VMSResponse;
import com.gfx.web.base.dto.VMSResponseFactory;
import com.gfx.web.common.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * @param roleId 角色id
     * @return
     */
    @GetMapping("/getUserByRoleId")
    public Map<String,Object> getUserByRoleId(@RequestParam("roleId")String roleId){
        VMSResponse vmsResponse = VMSResponseFactory.newInstance();
        vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_ERROR);
        //查询业务员集合
        List<User> list = userManageService.getUserByRoleId(roleId);
        if (list.size()>0){
            vmsResponse.setResponseBodyData(list);
            vmsResponse.setResponseBodyResult(VMSResponse.RESPONSE_RESULT_SUCCESS);
        }
        return vmsResponse.generateResponseBody();
    }

}
