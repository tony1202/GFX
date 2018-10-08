package com.gfx.web.base.service;

import com.gfx.web.base.dto.AccessDto;
import com.gfx.web.base.operate.dto.SystemLogPagination;

import java.util.Map;

/**
 * @author tony
 * @date 2018/9/6
 * @Description: 系统操作记录
 */
public interface SystemLogService {
    /**登入操作*/
    String ACCESS_TYPE_LOGIN = "login";
    /**登出操作*/
    String ACCESS_TYPE_LOGOUT = "logout";

    /**
     * 新增用户访问记录
     * @param userId 用户id
     * @param userName 用户名
     * @param accessIp 用户ip
     * @param accessType 访问类型
     */
    void addAccessRecord(String userId, String userName,String accessIp, String accessType);

    /**
     * 密码修改
     * @param accessDto
     * @return
     */
    String passwordModify(AccessDto accessDto);

    /**
     * 获取访问记录
     * @param pagination 分类条件
     * @return
     */
    Map<String, Object> getAccessRecords(SystemLogPagination pagination);
}
