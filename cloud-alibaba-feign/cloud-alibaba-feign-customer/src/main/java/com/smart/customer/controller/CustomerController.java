package com.smart.customer.controller;

import com.smart.customer.feign.ProducerService;
import com.smart.customer.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author mtl
 */
@RestController
public class CustomerController {

    @Resource
    ProducerService producerService;


    @GetMapping("/1")
    public String test() {
        String test = producerService.test(1);
        return test;
    }

    @GetMapping("/2")
    public String test2(){
        return producerService.test2();
    }

    @ResponseBody
    @GetMapping("/3")
    public User test3(){
        return producerService.test3();
    }

}
