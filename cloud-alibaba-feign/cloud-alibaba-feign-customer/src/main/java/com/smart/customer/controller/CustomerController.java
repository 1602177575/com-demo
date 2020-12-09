package com.smart.customer.controller;

import com.smart.customer.feign.ProducerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/cus")
public class CustomerController {

    @Resource
    ProducerService producerService;

    @GetMapping("/")
    public String test() {
        String test = producerService.test(1);
        return test;
    }
}
