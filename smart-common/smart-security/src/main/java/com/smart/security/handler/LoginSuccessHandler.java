package com.smart.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.security.dto.ResponseResult;
import com.smart.security.dto.UserDto;
import com.smart.security.utils.ResposeUtils;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 登陆成功返回
 * @author mtl
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        UserDto dto = (UserDto) authentication.getPrincipal();
        ResposeUtils.responseToJson(httpServletResponse,objectMapper, ResponseResult.success(dto));

    }
}
