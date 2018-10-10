package com.gfx.web.app.userManage.service;

import com.gfx.web.app.userManage.dto.UserDto;
import com.gfx.web.base.dto.Pagination;
import com.gfx.web.common.entity.Role;
import com.gfx.web.common.entity.User;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取员工列表
     * @param pagination 分页条件
     * @return 员工数据
     */
    Map<String,Object> getUserList(Pagination pagination);

    /**
     * 更新员工信息
     * @param user 员工信息
     * @return 更新结果
     */
    boolean updateUserAdmin(UserDto user);

    /**
     * 删除员工信息
     * @param userId 员工id
     * @return
     */
    boolean deleteUserAdmin(String userId);

    /**
     * 新增员工
     * @param user 员工信息
     * @return
     */
    String addUserAdmin(UserDto user);

    /**
     * 查询角色列表
     * @return
     */
    List<Role> getRoleList();
}
