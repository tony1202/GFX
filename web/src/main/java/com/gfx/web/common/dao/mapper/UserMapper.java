package com.gfx.web.common.dao.mapper;

import com.gfx.web.common.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserMapper extends Mapper<User> {

    User getUserById(@Param("userId") String userId);

    List<User> getUserListByRoleId(@Param("roleId") String roleId);

    int checkUserexists(@Param("userId") String userId, @Param("oldPassWord") String oldPassWord);

    int updatePassWord(@Param("userId") String userId, @Param("newPassWord") String newPassWord);
}