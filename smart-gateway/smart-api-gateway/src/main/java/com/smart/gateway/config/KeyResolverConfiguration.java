package com.smart.gateway.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.concurrent.Exchanger;


@Slf4j
@Configuration
public class KeyResolverConfiguration {


        /**IP地址限流*/
    @Bean("ipKeyResolver")
    public KeyResolver ipKeyResolver () {
        log.info("触发IP限流");
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }



}
