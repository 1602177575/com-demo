package com.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * topic 发送消息队列的名称  consumerGroup 表示你要订阅那个组的消息
 * 第一轮接收数据  grp组2的处理
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "tp_consumer_1",consumerGroup = "consumer_grp_02")
public class MyRocketMQDemo implements RocketMQListener<String> {

    //延迟处理的topic  延迟一段时间后趣查询订单是否完成系列操作
    final String destination="tp_consumer_2";

    @Resource
    RocketMQTemplate rocketMQTemplate;

    @Override
    public void onMessage(String s) {
        //创建了订单 存到队列中
        rocketMQTemplate.syncSend(destination, MessageBuilder.withPayload(s).build(),200,3);
        //后续做数据库减库存操作
    }
}
