package com.smart.customer.feign;

import com.smart.customer.pojo.User;
import org.springframework.stereotype.Component;


@Component
public class ProducerServiceFallback implements ProducerService {


    @Override
    public String test(Integer uid) {
        System.out.println("执行test方法 熔断限流");
        return null;
    }

    @Override
    public String test2() {
        System.out.println("执行test2方法 熔断限流");
        return null;
    }

    @Override
    public User test3() {
        System.out.println("执行test3方法 熔断限流");
        return null;
    }
}
