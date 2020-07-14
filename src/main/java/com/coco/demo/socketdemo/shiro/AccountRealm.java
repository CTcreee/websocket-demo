package com.coco.demo.socketdemo.shiro;

import com.coco.demo.socketdemo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class AccountRealm extends AuthorizingRealm {
    @Lazy
    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        AccountProfile principal = (AccountProfile) principalCollection.getPrimaryPrincipal();

        if(principal.getUsername().equals("arkcrow")){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addRole("admin");
            return info;
        }

        return null;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        AccountProfile profile = userService.login(token.getUsername(), String.valueOf(token.getPassword()));
        // 把用户信息存到session中，方便前端展示
        SecurityUtils.getSubject().getSession().setAttribute("profile", profile);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(profile, token.getCredentials(), getName());
        return info;


    }
}
