package com.coco.demo.socketdemo.config;

import com.coco.demo.socketdemo.shiro.AccountRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
@Lazy
@Configuration
public class ShiroConfig {

    @Bean
    AccountRealm accountRealm() {
        return new AccountRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager(AccountRealm accountRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(accountRealm);
        return securityManager;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        // logged in users with the 'admin' role
        chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");


        chainDefinition.addPathDefinition("/login.html", "anon");
        chainDefinition.addPathDefinition("/toLogin", "anon");

        // all other paths require a logged in user
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }




}
