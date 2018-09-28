package com.gfx.web.app.userManage.service;

import com.gfx.web.common.entity.User;

import java.util.List;

/**
 * @author tony
 * @date 2018/9/28
 */
public interface UserManageService {
    /**
     * 根据角色查用户
     * @param roleId 角色id
     * @return
     */
    List<User> getUserByRoleId(String roleId);
}
