package com.smart.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
