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

    int deleteByUserId(@Param("userId") String userId);

    /**
     * 新增用户与角色的关系 - 采用一次insert 多个values的形式
     * @param userId
     * @param roleIds
     */
    void addRoles(@Param("userId") String userId, @Param("roleIds") List<String> roleIds);

    /**
     * 新增用户与角色关系 - 采用多次执行insert语句的方式
     * @param userId
     * @param roleIds
     * @return
     */
    int addRoles2(@Param("userId") String userId, @Param("roleIds") List<String> roleIds);
}