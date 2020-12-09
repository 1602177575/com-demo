package com.smart.redis.controller;


import com.alibaba.fastjson.JSON;
import com.smart.redis.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    RedisService redisService;

    @GetMapping("/")
    String test(){
        String user = JSON.toJSONString(redisService.get("user"));
        return "测试"+user;
    }

    @GetMapping("/add")
    void add(@RequestPart("date") String date){
        redisService.set("user",date);

    }
}
