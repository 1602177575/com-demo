package com.smart.quartz.controller;

import com.smart.quartz.service.QuzrtzService;
import org.quartz.SchedulerException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("test")
public class quartzController {

    @Resource
    QuzrtzService quzrtzService;

    @GetMapping("/1")
    public void test() throws SchedulerException {
        quzrtzService.test();
    }

}
