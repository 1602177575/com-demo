package com.smart.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author mtl
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayAppincation {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayAppincation.class,args);
    }
}
