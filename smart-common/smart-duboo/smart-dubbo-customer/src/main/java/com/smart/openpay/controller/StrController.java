package com.smart.openpay.controller;

import com.smart.openpay.service.TestService;
import com.smart.pay.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class StrController {

    @Resource
    TestService testService;


    @GetMapping("/test")
   public String Str(){
        return testService.test();
    }


    @GetMapping("/test1")
    public User test1(){
        User user = testService.test1();
        log.info("用户信息"+user.getName());
        return user;
    }



}
