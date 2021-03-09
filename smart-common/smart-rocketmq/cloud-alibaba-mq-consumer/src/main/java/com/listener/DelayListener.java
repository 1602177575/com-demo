package com.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(topic = "tp_consumer_2",consumerGroup = "consumer_grp_01")
public class DelayListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        log.info("接收到数据:"+s);
    }

}
