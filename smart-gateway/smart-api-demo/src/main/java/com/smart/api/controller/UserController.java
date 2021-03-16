package com.smart.api.controller;

import com.smart.api.utils.CookieUtils;
import com.smart.api.utils.UserUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mtl
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/test")
    String test(){
        return "测试";
    }

    //存入用户信息
    @GetMapping("/set")
    void setUserTest(HttpServletRequest request,HttpServletResponse response,String name){
        CookieUtils.setCookie(request,response,"tokenUser","user:666"+name);
    }


    @GetMapping("/get")
    String getUserTest(){
        String tokenUserName = UserUtils.getTokenUserName();
        System.out.println(tokenUserName);
        return tokenUserName;
    }



}
