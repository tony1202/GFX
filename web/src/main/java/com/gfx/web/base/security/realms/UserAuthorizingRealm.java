package com.gfx.web.base.security.realms;

import com.gfx.web.base.constant.VMSConstant;
import com.gfx.web.base.context.UserContextHolder;
import com.gfx.web.base.dto.UserInfoDto;
import com.gfx.web.base.service.UserService;
import com.gfx.web.base.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tony
 * @date 2018/9/6
 * @Description: 用户认证与授权
 */
@Component
public class UserAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 用户授权
     *
     * @param principals 用户信息
     * @return 用户授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        UserInfoDto userInfo = (UserInfoDto) session.getAttribute(VMSConstant.SessionConstant.USER_INFO);
        Set<String> roles = new HashSet<>(userInfo.getRoles());
        Set<String> permission = new HashSet<>(userInfo.getPermissions());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
        simpleAuthorizationInfo.setStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     *
     * @param token 用户凭证
     * @return 用户认证信息
     * @throws AuthenticationException 认证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String realmName = getName();
        String credentials = "";

        try {
            // 获取用户名对应的用户账户信息
            UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
            //获取用户id
            String userId = usernamePasswordToken.getUsername();
            if (StringUtils.isNotBlank(userId)) {

                //从数据库查询用户信息
                UserInfoDto userInfo = userService.getUserInfo(userId);
                if (userInfo != null) {
                    Subject subject = SecurityUtils.getSubject();
                    Session session = subject.getSession();
                    //将用户信息写入到
                    session.setAttribute(VMSConstant.SessionConstant.USER_INFO, userInfo);

                    //结合验证码对密码进行处理
                    String checkCode = (String) session.getAttribute(VMSConstant.SessionConstant.CHECK_CODE);
                    String passWord = userInfo.getPassWord();
                    if (StringUtils.isNoneBlank(checkCode, passWord)) {
                        checkCode = checkCode.toUpperCase();
                        credentials = MD5Util.MD5(passWord+checkCode);
                    }

                    //清楚useInfo中密码
                    userInfo.setPassWord(null);
                    //存储用户信息
                    //UserContextHolder.setUserInfo(userInfo);
                }
            }
            return new SimpleAuthenticationInfo(userId,credentials,realmName);
        } catch (InvalidSessionException e) {
            throw new AuthenticationException(e.getMessage());
        }

    }
}
