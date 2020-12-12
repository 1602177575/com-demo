package com.alibaba.smart.Controller;

import com.alibaba.smart.Service.RocketMQProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MsgController {

    @Resource
    RocketMQProducer rocketMQProducer;

    @GetMapping("/send")
    void sendMsg(){
        rocketMQProducer.sendMsg("测试1");

    }

}
