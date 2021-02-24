package com.smart.sec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RouterController {

    //跳到主页
    @RequestMapping("/")
    public String index(){
        return "index";
    }


}
