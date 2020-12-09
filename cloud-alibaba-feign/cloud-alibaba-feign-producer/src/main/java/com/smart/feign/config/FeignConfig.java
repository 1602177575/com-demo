package com.smart.feign.config;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public IRule rule() {
        // 轮询权重
//        return new WeightedResponseTimeRule();
//        return new RandomRule();
//        最少并发策略
        return new BestAvailableRule();
    }




}
