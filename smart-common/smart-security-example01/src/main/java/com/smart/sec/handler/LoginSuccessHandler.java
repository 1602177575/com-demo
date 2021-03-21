package com.smart.sec.handler;

import cn.hutool.core.lang.UUID;
import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.sec.Response.R;
import com.smart.sec.Response.ResponseResult;
import com.smart.sec.utils.CookieUtils;
import com.smart.sec.utils.JWTService;
import com.smart.sec.utils.RedisUtils;
import com.smart.sec.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;


/** 登陆成功返回
 * 如何获取用户信息？
 *
 * java jwt 成功回调  为什么不走controller ?
 *
 *  实现加密
 *
 *
 * @author mtl
 */
@Component
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    ResponseUtils responseUtils;

    @Resource
    JWTService jwtService;

    @Resource
    ObjectMapper objectMapper;




    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
               log.info("登陆成功");
        String name = authentication.getName();
        //根据用户名获取用户信息
        response.setContentType("application/json;charset=utf-8");
        String token = jwtService.generateToken(name);
        log.info("转换为token的用户name: "+jwtService.getUserNameFromToken(token));
        //生成JWT

        //放入头文件
        response.setHeader("token",token);
        responseUtils.responseToJson(response,objectMapper, ResponseResult.success(token));

    }
}
