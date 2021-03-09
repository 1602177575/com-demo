package com.smart.log.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Configuration
public class LogRecordAspect {

    @Before("StuInfoAsp()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes sc = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sc.getRequest();
        log.info("uri--"+request.getRequestURI());
        log.info("port--"+request.getServerPort());
        log.info("ip--"+request.getRemoteHost());
        log.info("method"+request.getMethod());
        log.info("value"+request.getQueryString());
    }

}