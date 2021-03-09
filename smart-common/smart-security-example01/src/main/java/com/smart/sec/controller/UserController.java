package com.smart.sec.controller;

import com.smart.sec.utils.JWTService;
import com.smart.sec.utils.SpringSecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;


@Controller
@Slf4j
public class UserController {

    @Resource
    SpringSecurityUtils springSecurityUtils;
    /**
     * session获取用户信息
     * @return
     */
    @RolesAllowed("USER")
    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public void currentUserNameSimple(HttpServletRequest request) {
        String s = springSecurityUtils.currentUser(request.getSession());
        log.info("当前用户"+s);

    }
}
