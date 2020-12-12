package com.smart.openpay.controller;

import com.smart.openpay.service.TestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StrController {

    @Resource
    TestService testService;


    @PostMapping("/test")
    String Str(){
        return testService.test();
    }

}
