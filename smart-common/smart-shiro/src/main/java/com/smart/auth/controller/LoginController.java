package com.smart.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoginController {


    @GetMapping("/login")
    public String AdminControllerAdminControllerlogin(String username,String password){
        //创建  UsernamePasswordToken
        UsernamePasswordToken token=new UsernamePasswordToken(username, password);
        //获取 Subject
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            log.info("登录成功");
        } catch (AccountException e) {
            log.info("账号异常");
        } catch (IncorrectCredentialsException exception) {
            //账号密码错误
            log.info("账号密码错误!");
        }
        return "success";
    }


}
