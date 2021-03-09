package com.smart.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 未授权报错界面
 */
@RestController
@RequestMapping("/Ex")
public class ExceptionController {

    @RequestMapping("/401")
    public String Exception(){
        return "用户未经授权禁止访问";
    }

}
