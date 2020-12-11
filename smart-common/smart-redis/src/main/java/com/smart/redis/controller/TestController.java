package com.smart.redis.controller;


import com.alibaba.fastjson.JSON;
import com.smart.redis.service.RedisService;
import javafx.scene.chart.PieChart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    void add(@RequestPart("date") String date,@RequestPart("address") String address){
        redisService.set(address,date);
    }

    /**
     * 时间单位, 天:TimeUnit.DAYS 小时:TimeUnit.HOURS 分钟:TimeUnit.MINUTES
     *      *                秒:TimeUnit.SECONDS 毫秒:TimeUnit.MILLISECONDS
     */
    @GetMapping("set")
    void set(String key,String value,long date){
        redisService.setEx(key,value,date, TimeUnit.SECONDS);
    }


    @GetMapping("/uptime")
    void updateTime(String key,Date data){
        Boolean expire = redisService.expireAt(key,data);
        System.out.println(expire);
    }


}
