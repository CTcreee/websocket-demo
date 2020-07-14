package com.coco.demo.socketdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wangzz
 */
@SpringBootApplication
@MapperScan("com.coco.demo.socketdemo.dao")
public class SocketDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocketDemoApplication.class, args);
    }

}
