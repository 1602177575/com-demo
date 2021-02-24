package com.smart.sec.handler;

/**
 * 登陆出错返回
 * @author mtl
 */


import com.smart.sec.Response.ResponseResult;
import com.smart.sec.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Slf4j
public class LoginErroHandler implements AuthenticationFailureHandler {

    @Resource
    ResponseUtils responseUtils;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.info(e.getMessage()+"登陆错误");
        log.info(e.getStackTrace()+"登陆错误");
        response.setContentType("application/json");
        responseUtils.responseToJson(response, ResponseResult.error("登陆失败"));
    }
}