package com.gfx.web.common.dao.mapper;

import com.gfx.web.common.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface UserMapper extends Mapper<User> {

    User getUserById(@Param("userId") String userId);
}