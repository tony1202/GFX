package com.gfx.web.common.dao.mapper;

import com.gfx.web.app.userManage.dto.UserDto;
import com.gfx.web.common.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper extends Mapper<User> {

    User getUserById(@Param("userId") String userId);

    List<User> getUserListByRoleId(@Param("roleId") String roleId);

    int checkUserexists(@Param("userId") String userId, @Param("oldPassWord") String oldPassWord);

    int updatePassWord(@Param("userId") String userId, @Param("newPassWord") String newPassWord);

    List<UserDto> getUserList(@Param("params") Map<String, Object> params);

    int deleteUserAdmin(@Param("userId") String userId);

    /**
     * 根据用户姓名拼音查询已经从存在的用户数,来确认用户id
     * @param pinyin
     * @return
     */
    int findUserNum(@Param("pinyin") String pinyin);

    /**
     * 查询符合分页条件的记录总数
     * @param params
     * @return
     */
    long getTotal(@Param("params") Map<String, Object> params);
}