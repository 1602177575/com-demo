package com.smart.sec.controller;

import com.smart.sec.utils.JWTService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
public class UserController {

    @Resource
    JWTService jwtService;

    /**
     * 根据token 获取用户信息
     * @return
     */
    @RequestMapping(value = "/getName", method = RequestMethod.GET)
    public String currentUserNameSimple(String token) {
       return jwtService.getUserNameFromToken(token);
    }
}
