package com.smart.openpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 消费者
 * 注册于被发现
 */
@SpringBootApplication
@EnableDiscoveryClient
public class DubboApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboApplication.class,args);
    }
}
