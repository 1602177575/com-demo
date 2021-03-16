package com.smart.sec.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

/**
 * 测试接口
 * @DenyAll 和 @PermitAll 代表全部拒绝和全部通过
 *
 * @RolesAllowed({"USER", "ADMIN"})
 * 代表标注的方法只要具有USER, ADMIN任意一种权限就可以访问。这里可以省略前缀ROLE_，实际的权限可能是ROLE_ADMIN。
 */
@Controller
@Slf4j
public class VipController {



    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/list")
    public String userList(){
        log.info("用户列表权限");
        return "user/list";
    }

    @RolesAllowed({"USER","ADMIN"})
    @RequestMapping("/user/add")
    public String userAdd(){
        log.info("添加用户权限");
        return "user/add";
    }


    @RolesAllowed("ADMIN")
    @RequestMapping("/user/update")
    public String userUp(){
        return "user/update";
    }


    @RolesAllowed("VIP")
    @RequestMapping("/level/vip")
    public String vip(){
        return "level/vip";
    }

    @PermitAll
    @RequestMapping("/test/403")
    public String Ex403(){
        return "user/403";
    }


}
