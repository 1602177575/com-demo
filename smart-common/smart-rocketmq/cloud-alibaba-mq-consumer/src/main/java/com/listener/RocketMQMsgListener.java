package com.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * rocketmq 消息监听，@RocketMQMessageListener中的selectorExpression为tag，默认为*
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "hello-topic",selectorExpression="*",consumerGroup = "queue_group_test")
public class RocketMQMsgListener implements RocketMQListener<String> {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void onMessage(String message) {
        log.info("接收到消息"+message);
    }

}