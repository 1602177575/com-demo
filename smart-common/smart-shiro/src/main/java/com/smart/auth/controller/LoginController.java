package com.smart.auth.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mtl
 */
@Controller
@Slf4j
public class LoginController {


    @RequestMapping("/index")
    public String tologin(Model model){
        model.addAttribute("msg","hello shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "user/add";
    }


    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "logout";
    }


    @RequestMapping("/login")
    public String login(String username,String password,Model model){
        //获取当前用户
        Subject subject= SecurityUtils.getSubject();
        //封装当前登陆的用户
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        try {
            subject.login(usernamePasswordToken);//执行异常 如果没问题就通过了
            //登陆成功去首页
            return "index";
        }catch (UnknownAccountException e){
            //用户名不存在 登陆失败就去登陆界面
            log.info("用户名不存在");
            model.addAttribute("msg","用户不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            //密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }

}
