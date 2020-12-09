package com.smart.feign.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pro")
public class ProducerController {
    @Value("${server.port}")
    private int port;

    @GetMapping("/")
    public String test(int uid) {
        return port + "";
    }
}
