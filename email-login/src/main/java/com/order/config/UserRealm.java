package com.order.config;


import com.order.pojo.User;
import com.order.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ Author   ：clt.
 * @ Date     ：Created in 12:26 2019/8/6
 * @author Mrchen
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     *执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        info.addRole(user.getRole());
        System.out.println(user);
        return info;
    }

    /**
     *执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        List<User> users = userService.findByUserName(token.getUsername());
        for (User user : users) {
            if (user == null){
                return null;
            } else {
                if ("0".equals(user.getState())){
                    throw new LockedAccountException();
                }
            }
            ByteSource salt = ByteSource.Util.bytes(user.getUserName());
            return new SimpleAuthenticationInfo(user,user.getPassWord(),salt,"userRealm");
        }
        return null;
    }
}

