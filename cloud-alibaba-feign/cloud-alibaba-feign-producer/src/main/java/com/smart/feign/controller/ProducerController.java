package com.smart.feign.controller;

import com.smart.feign.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pro")
public class ProducerController {
    @Value("${server.port}")
    private int port;

    @PostMapping("/fin")
    public String test(Integer uid) {
        return port + (String.valueOf(uid));
    }

    @PostMapping("/t2")
    public String test2(){
        return "测试二号接口";
    }

    @PostMapping("/t3")
    User test3(){
        User user = new User();
        user.setName("测试");
        user.setPassword("123");
        return user;
    }


}
