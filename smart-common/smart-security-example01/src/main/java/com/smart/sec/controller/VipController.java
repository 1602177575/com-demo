package com.smart.sec.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * 测试接口
 */
@Controller
@Slf4j
public class VipController {



    @RolesAllowed("USER")
    @RequestMapping("/list")
    public String userList(){
        log.info("用户列表权限");
        return "user/list";
    }

    @RolesAllowed("USER")
    @RequestMapping("/user/add")
    public String userAdd(){
        log.info("添加用户权限");
        return "user/add";
    }

    @RolesAllowed("USER")
    @RequestMapping("/user/update")
    public String userUp(){
        return "user/update";
    }


    @RolesAllowed("VIP")
    @RequestMapping("/level/vip")
    public String vip(){
        return "level/vip";
    }

    @RequestMapping("/test/403")
    public String Ex403(){
        return "user/403";
    }


}
