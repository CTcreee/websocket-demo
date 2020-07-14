package com.coco.demo.socketdemo.service;

import com.coco.demo.socketdemo.entity.Webuser;
import com.coco.demo.socketdemo.shiro.AccountProfile;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    void deleteByPrimaryKey(Integer id);

    void insert(Webuser record);

    Webuser selectByPrimaryKey(Integer id);

    List<Webuser> selectAll();

    void updateByPrimaryKey(Webuser record);

    String selectByusername(String username);

    AccountProfile login(String username, String password);
}
