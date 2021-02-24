package com.smart.sec.handler;

import com.smart.sec.Response.ResponseResult;
import com.smart.sec.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class OnLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    ResponseUtils responseUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
        log.info("去登陆界面");
        responseUtils.responseToJson(resp, ResponseResult.success("请去登陆"));

    }
}
