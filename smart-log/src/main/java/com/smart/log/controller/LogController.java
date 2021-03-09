package com.smart.log.controller;


import com.smart.log.Action.Cache;
import com.smart.log.Action.MethodLogAction;
import com.smart.log.pojo.TestLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {

    @GetMapping("/t2")
    @MethodLogAction(name = "进行测试")
    public String test2(){
        log.info("执行测试");
        return "测试";
    }
}
