package com.alibaba.smart.Service;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import javax.websocket.SendResult;

/**
 * 生产者使用
 *  RocketMQTemplate 直接使用
 */
@Service
public class RocketMQProducer{

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Value("${rocketmq.topic}")
    private String topics;

    /**
     * 发送普通消息 异步
     *
     * String destination 目的地 主题 目标客户端
     *  Object payload, 消息 对象
     *  sendCallback  异步接口回调
     *  int delayLevel 延迟消息 订单状态  例子：1分钟内处理支付 否则取消订单修改订单状态
     *
     *
     */
    public void sendMsg(String msgBody){
        /**
         * 同步消息
         */

        rocketMQTemplate.asyncSend(topics, msgBody, new SendCallback() {
            @Override
            public void onSuccess(org.apache.rocketmq.client.producer.SendResult sendResult) {
                System.out.println(sendResult.getSendStatus()+"");
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        });
    }

    /**
     * 发送异步消息 在SendCallback中可处理相关成功失败时的逻辑
     */
    public void sendAsyncMsg(String msgBody){
        rocketMQTemplate.asyncSend("queue_test_topic", MessageBuilder.withPayload(msgBody).build(), new SendCallback() {

            public void onSuccess(SendResult sendResult) {
                // 处理消息发送成功逻辑
            }

            @Override
            public void onSuccess(org.apache.rocketmq.client.producer.SendResult sendResult) {
            }
            @Override
            public void onException(Throwable e) {
                // 处理消息发送异常逻辑
            }
        });
    }

    /**
     * 发送延时消息<br/>
     * 在start版本中 延时消息一共分为18个等级分别为：1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h<br/>
     */
//    public void sendDelayMsg(String msgBody, Integer delayLevel){
//        rocketMQTemplate.syncSend("queue_test_topic",MessageBuilder.withPayload(msgBody).build(),messageTimeOut,delayLevel);
//    }

    /**
     * 发送带tag的消息,直接在topic后面加上":tag"
     */
    public void sendTagMsg(String msgBody){
        rocketMQTemplate.syncSend("queue_test_topic:tag1",MessageBuilder.withPayload(msgBody).build());
    }

}
