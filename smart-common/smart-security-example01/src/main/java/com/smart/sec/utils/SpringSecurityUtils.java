package com.smart.sec.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

@Component
@Slf4j
public class SpringSecurityUtils {

    //获取当前用户信息
    public  String currentUser(HttpSession session) {
        SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        return ((UserDetails)securityContext.getAuthentication().getPrincipal()).getUsername();
    }

}
