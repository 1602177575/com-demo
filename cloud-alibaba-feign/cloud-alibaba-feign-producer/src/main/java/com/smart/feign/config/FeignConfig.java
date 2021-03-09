package com.smart.feign.config;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public IRule rule() {
        // 轮询+权重
      return new WeightedResponseTimeRule();
      //随机
//       return new RandomRule();
//        最少并发策略
//        return new BestAvailableRule();
    }




}
