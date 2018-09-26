package com.gfx.web.base.context;

import com.gfx.web.base.constant.VMSConstant;
import com.gfx.web.base.dto.UserInfoDto;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Optional;

/**
 * @author tony
 * @date 2018/9/11
 * @Description:
 */

public class UserContextHolder {
   // private static final ThreadLocal<UserInfoDto> USERINFO_THREAD_LOCALTHREADLOCAL = new ThreadLocal<>();

    private static final RequestAttributes REQUEST_ATTRIBUTES = RequestContextHolder.getRequestAttributes();

    /**
     * 获取存储在session中的用户信息
     * @return 用户信息实体
     */
    public static UserInfoDto getUserInfo(){
        Optional<UserInfoDto> userInfoOptional = Optional.ofNullable((UserInfoDto) REQUEST_ATTRIBUTES.getAttribute(VMSConstant.SessionConstant.USER_INFO,RequestAttributes.SCOPE_SESSION));
        //获取session中用户信息
        return userInfoOptional.orElseThrow(RuntimeException::new);

    }

    /**
     * 向Threadlocal中存入userInfo
     * @param userInfo 用户信息对象
     */
    public static void setUserInfo(UserInfoDto userInfo){
        if (userInfo==null)
            return;
        //USERINFO_THREAD_LOCALTHREADLOCAL.set(userInfo);
        REQUEST_ATTRIBUTES.setAttribute(VMSConstant.SessionConstant.USER_INFO,userInfo,RequestAttributes.SCOPE_SESSION);
    }
}
