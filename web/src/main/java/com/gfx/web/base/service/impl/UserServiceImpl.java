package com.gfx.web.base.service.impl;

import com.gfx.web.base.dto.UserInfoDto;
import com.gfx.web.base.service.UserService;
import com.gfx.web.common.dao.mapper.PermissionMapper;
import com.gfx.web.common.dao.mapper.RoleMapper;
import com.gfx.web.common.dao.mapper.UserMapper;
import com.gfx.web.common.entity.Permission;
import com.gfx.web.common.entity.User;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tony
 * @date 2018/9/6
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private PermissionMapper permissionMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, RoleMapper roleMapper, PermissionMapper permissionMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.permissionMapper = permissionMapper;
    }

    /**
     * 根据userId 获取用户信息
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public UserInfoDto getUserInfo(String userId) {
        UserInfoDto userInfo = new UserInfoDto();
        User user = userMapper.getUserById(userId);
        //如果id查不到用户,就表示用户不存在,返回null
        if (user==null){
            return null;
        }
        userInfo.setUserId(userId);
        userInfo.setUserName(user.getUserName());
        userInfo.setPassWord(user.getPassWord());
        List<String> roles = roleMapper.getRolesByUserId(userId);
        userInfo.setRoles(roles);
        if (CollectionUtils.isNotEmpty(roles)){
            List<String> permissions= permissionMapper.getPermissionsByRoles(roles);
            userInfo.setPermissions(permissions);
        }
        return userInfo;
    }
}
