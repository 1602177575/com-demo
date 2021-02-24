package com.smart.sec.controller;


import com.smart.sec.sevice.impl.CustomerUserDetailsServiceImpl;
import com.smart.sec.utils.CookieUtils;
import com.smart.sec.utils.JWTService;
import com.wf.captcha.utils.CaptchaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class CaptchaController {

    @Resource
    JWTService jwtService;


    @ResponseBody
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.out(request, response);
    }

    @RequestMapping("/test")
    public String test(HttpServletRequest request, HttpServletResponse response, Model model){
        response.setHeader("Authorization","测试数据 头文件");

        String username = jwtService.getUserNameFromToken(CookieUtils.getCookieValue(request, "token"));
        log.info("当前用户名"+username);

        model.addAttribute("username",username);

        return "index";
    }

}
