package com.gfx.web.app.userManage.service.impl;

import com.gfx.web.app.userManage.dto.UserDto;
import com.gfx.web.app.userManage.service.UserManageService;
import com.gfx.web.base.dto.Pagination;
import com.gfx.web.base.operate.UserOperation;
import com.gfx.web.base.util.PY4JUtil;
import com.gfx.web.common.dao.mapper.RoleMapper;
import com.gfx.web.common.dao.mapper.UserMapper;
import com.gfx.web.common.entity.Role;
import com.gfx.web.common.entity.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tony
 * @date 2018/9/28
 */
@Service
public class UserManageServiceImpl implements UserManageService {

    private static final Logger log = LoggerFactory.getLogger(UserManageServiceImpl.class);

    private UserMapper userMapper;
    private RoleMapper roleMapper;

    @Autowired
    public UserManageServiceImpl(UserMapper userMapper, RoleMapper roleMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
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

    /**
     * 获取员工列表
     *
     * @param pagination 分页条件
     * @return 员工数据
     */
    @Override
    public Map<String, Object> getUserList(Pagination pagination) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        if (pagination.getOffset()<0||pagination.getLimit()<0){
            return result;
        }
        if (StringUtils.equalsIgnoreCase(pagination.getSearchType(), "searchByName")) {
            params.put("name", pagination.getKeyWord());
        }
        if (StringUtils.equalsIgnoreCase(pagination.getSearchType(), "searchByPhone")) {
            params.put("phone", pagination.getKeyWord());
        }
        /**
         * 由于user与user_role存在一对多的关系,就会导致pageHelper分页数据不准,比实际要多,所以只能手动实现
         * Page<Object> page = PageHelper.startPage(pagination.getOffset(), pagination.getLimit(), true);
         */
        params.put("offset",pagination.getOffset());
        params.put("limit",pagination.getLimit());

        List<UserDto> list = userMapper.getUserList(params);
        result.put("total", (long)list.size());
        result.put("data", list);
        return result;
    }

    /**
     * 更新员工信息
     *
     * @param user 员工信息
     * @return 更新结果
     */
    @Override
    @UserOperation("更新员工信息")
    public boolean updateUserAdmin(User user) {
        user.setUpdateDate(new Date());
        return userMapper.updateByPrimaryKeySelective(user) > 0;
    }

    /**
     * 删除员工信息
     *
     * @param userId 员工id
     * @return
     */
    @Override
    @UserOperation("删除员工")
    public boolean deleteUserAdmin(String userId) {
        int i = userMapper.deleteUserAdmin(userId);
        return i > 0;
    }

    /**
     * 新增员工
     *
     * @param user 员工信息
     * @return
     */
    @Override
    public String addUserAdmin(User user) {
        String result=null;
        if (StringUtils.isNotBlank(user.getUserName())) {
            String userId = generateUserId(user.getUserName());
            user.setUserStatus("0");
            user.setCreateDate(new Date());
            user.setUpdateDate(new Date());
            user.setUserId(userId);
            if (userMapper.insertSelective(user) > 0){
                result = userId;
            }
        }
        return result;
    }

    /**
     * 查询角色列表
     *
     * @return
     */
    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }

    /**
     * 根据用户姓名生成用户id
     *
     * @param userName 用户姓名
     * @return
     */
    private String generateUserId(String userName) {
        String pinyin = PY4JUtil.toPinyin(userName);
        int num = userMapper.findUserNum(pinyin);
        DecimalFormat df = new DecimalFormat("000");
        String suf = df.format(num + 1);
        return pinyin + suf;
    }
}
