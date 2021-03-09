package com.smart.coupons.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RefreshScope
@Controller
public class TestController {

    @Value("${user.test}")
    private String username;

    @ResponseBody
    @GetMapping("/test")
    public String test(){
        System.out.println(username);
        return username;
    }


}
