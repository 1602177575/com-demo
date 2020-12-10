package com.smart.auth.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限控制
 * 权限注解
 * 基于角色的注解
 * 基于权限的注解
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/role")
    @RequiresRoles({"admin"})
    public String list() {
        return "需要admin权限才能访问";
    }

    @GetMapping("/per")
    @RequiresPermissions ({"/order/list","/admin"})
    public String per() {
        return "需要admin权限才能访问";
    }
}
