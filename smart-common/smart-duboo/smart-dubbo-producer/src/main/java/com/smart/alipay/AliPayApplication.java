package com.smart.alipay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * EnableDiscoveryClient 注册发现 将接口注册到微服务中心
 */

@SpringBootApplication
@EnableDiscoveryClient
public class AliPayApplication {
    public static void main(String[] args) {
        SpringApplication.run(AliPayApplication.class,args);
    }
}
