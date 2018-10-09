package com.gfx.web.common.dao.mapper;

import com.gfx.web.common.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface RoleMapper extends Mapper<Role> {
    List<String> getRolesByUserId(@Param("userId") String userId);

    List<Role> getRoleList();
}