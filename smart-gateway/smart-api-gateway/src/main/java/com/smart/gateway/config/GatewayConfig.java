package com.smart.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mtl
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator targetRouteLocator(RouteLocatorBuilder builder){
        return builder
                // 路由器唯一标识  //断言
                .routes().route("user-route",r -> r.path("/api/index/**")
                         //代理对象名称
                        .uri("lb://user-server"))
                        .build();
    }

}
