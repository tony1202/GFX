package com.gfx.web.base.context;

import com.gfx.web.base.dto.UserInfoDto;
import org.springframework.stereotype.Component;

/**
 * @author tony
 * @date 2018/9/11
 * @Description:
 */
@Component
public class UserContextHolder {
    private static final ThreadLocal<UserInfoDto> threadLocal = new ThreadLocal<>();

    /**
     * 获取存储在session中的用户信息
     * @return 用户信息实体
     */
    public static UserInfoDto getUserInfo(){
        //获取session中用户信息
        return threadLocal.get();
    }

    /**
     * 向Threadlocal中存入userInfo
     * @param userInfo 用户信息对象
     */
    public static void setUserInfo(UserInfoDto userInfo){
        threadLocal.set(userInfo);
    }
}
