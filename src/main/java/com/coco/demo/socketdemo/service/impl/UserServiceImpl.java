package com.coco.demo.socketdemo.service.impl;

import com.coco.demo.socketdemo.dao.WebuserMapper;
import com.coco.demo.socketdemo.entity.Webuser;
import com.coco.demo.socketdemo.service.UserService;
import com.coco.demo.socketdemo.shiro.AccountProfile;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
   @Autowired
    com.coco.demo.socketdemo.dao.WebuserMapper webuserMapper;
    @Override
    public void deleteByPrimaryKey(Integer id) {
        webuserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Webuser record) {
        webuserMapper.insert(record);
    }

    @Override
    public Webuser selectByPrimaryKey(Integer id) {
        return webuserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Webuser> selectAll() {
        return webuserMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(Webuser record) {
        webuserMapper.updateByPrimaryKey(record);
    }

    @Override
    public String selectByusername(String username) {
        return webuserMapper.selectByusername(username);
    }

    @Override
    public AccountProfile login(String username, String password) {
        //TODO 查库，然后匹配密码是否正确！

        if(webuserMapper.selectByusername(username)==null) {
            // 抛出shiro异常，方便通知用户登录错误信息
            throw new UnknownAccountException("用户不存在");
        }

        if(!webuserMapper.selectByusername(username).equals(password)) {
            throw new IncorrectCredentialsException("密码错误");
        }

        AccountProfile profile = new AccountProfile();
        profile.setId(1);
        profile.setUsername(username);
        profile.setSign("sadsadsad");

        return profile;
    }

}
