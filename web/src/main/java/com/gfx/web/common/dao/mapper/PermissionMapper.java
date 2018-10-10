package com.gfx.web.common.dao.mapper;

import com.gfx.web.common.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
@Repository
public interface PermissionMapper extends Mapper<Permission> {
    List<String> getPermissionsByRoles(@Param("roles") List<String> roles);
}