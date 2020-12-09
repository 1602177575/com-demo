package com.smart.customer.service.impl;

import com.smart.customer.feign.ProducerService;
import com.smart.customer.mapper.UserMapper;
import com.smart.customer.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    ProducerService producerService;

    @Override
    public String getOrders() {
        String test = producerService.test(1);
        return null;
    }
}
