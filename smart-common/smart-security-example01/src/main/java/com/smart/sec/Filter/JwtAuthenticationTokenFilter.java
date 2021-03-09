package com.smart.sec.Filter;




import com.smart.sec.Response.ResponseResult;
import com.smart.sec.utils.CookieUtils;
import com.smart.sec.utils.JWTService;
import com.smart.sec.utils.RedisUtils;
import com.smart.sec.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author codermy 过滤器
 * @createTime 2020/7/30
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    @Resource
    RedisUtils redisUtils;
    @Resource
    JWTService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        log.info("过滤器");
        if(SecurityContextHolder.getContext().getAuthentication()!=null){

            String requestURI = request.getRequestURI();
            String remoteAddr = request.getRemoteAddr();
            String method = request.getMethod();
            log.info("当前URL："+requestURI);
            log.info("请求地址："+remoteAddr);
            log.info("请求方法："+method);
            String uuid = CookieUtils.getCookieValue(request, "user");
            if(uuid!=null){
                String token = (String) redisUtils.get(uuid);
                String username = jwtService.getUserNameFromToken(token);
                log.info("当前用户名"+username);
            }else {
                log.info("未登录");
            }

        }else{
            ResponseUtils.responseToJson(response,ResponseResult.error("滚去登陆"));
        }
        response.reset();
        chain.doFilter(request, response);
     }
    }

