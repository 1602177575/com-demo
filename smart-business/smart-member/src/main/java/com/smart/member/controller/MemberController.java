package com.smart.member.controller;


import com.smart.db.entity.User;
import com.smart.db.service.DbService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MemberController {

    @GetMapping("/")
    String test(){

        return "测试2";
    }

}
