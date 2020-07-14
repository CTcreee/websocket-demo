package com.coco.demo.socketdemo.shiro;

import lombok.Data;

import java.io.Serializable;
@Data
public class AccountProfile implements Serializable {
    private Integer id;
    private String Username;

    private String gender;
    private String sign;

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getUsername() {
        return Username;
    }

    public String getGender() {
        return gender;
    }

    public String getSign() {
        return sign;
    }
}
