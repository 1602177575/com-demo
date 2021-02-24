package com.smart.sec;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *
 * EnableGlobalMethodSecurity 开启了方法执行权限检查 后检查 和Secured
 *
 */

@SpringBootApplication
@MapperScan("com.smart.**.mapper")
public class SecurityExample01App {
    public static void main(String[] args) {
        SpringApplication.run(SecurityExample01App.class, args);
    }
}
