package com.smart.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Subject 用户
 * SecurityManager 管理所有用户
 * Realm 链接数据
 *
 * @author mtl
 */
@SpringBootApplication
@MapperScan("com.smart.auth.mapper")
public class ShiRoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiRoApplication.class,args);
    }
}
