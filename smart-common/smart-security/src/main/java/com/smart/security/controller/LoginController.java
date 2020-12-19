package com.smart.security.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mtl
 */
@RestController
public class LoginController {

    @GetMapping("/login")
    String login(){
        return "login";
    }
}
