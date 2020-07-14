package com.coco.demo.socketdemo.dao;

import com.coco.demo.socketdemo.entity.Webuser;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public interface WebuserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Webuser record);

    Webuser selectByPrimaryKey(Integer id);

    List<Webuser> selectAll();

    int updateByPrimaryKey(Webuser record);

    String selectByusername(String username);
}