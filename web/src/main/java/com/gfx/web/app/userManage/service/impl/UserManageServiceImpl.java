package com.gfx.web.app.userManage.service.impl;

import com.gfx.web.app.userManage.service.UserManageService;
import com.gfx.web.common.dao.mapper.UserMapper;
import com.gfx.web.common.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tony
 * @date 2018/9/28
 */
@Service
public class UserManageServiceImpl implements UserManageService {

    private static final Logger log = LoggerFactory.getLogger(UserManageServiceImpl.class);

    private UserMapper userMapper;

    @Autowired
    public UserManageServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 根据角色查用户
     *
     * @param roleId 角色id
     * @return
     */
    @Override
    public List<User> getUserByRoleId(String roleId) {

        return userMapper.getUserListByRoleId(roleId);
    }
}
