package com.gfx.web.app.userManage.dto;

import com.gfx.web.common.entity.Role;
import com.gfx.web.common.entity.User;

import java.util.List;

/**
 * @author tony
 * @date 2018/10/9
 */
public class UserDto extends User{
    private List<Role> roles;
    private List<String> roleIds;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }
}
