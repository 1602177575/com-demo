package com.alibaba.smart.Controller;

import com.alibaba.smart.Service.RocketMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class MsgController {

    private static final String destination ="tp_consumer_1";

    @Resource
    RocketMQTemplate rocketMQTemplate;


    @PostMapping("/buy")
    public void buy(){
        rocketMQTemplate.convertAndSend(destination,"发送消息卢本伟牛逼");
    }

}
