package com.smart.api.Filter;

import com.alibaba.fastjson.JSON;
import com.smart.api.utils.CookieUtils;
import com.smart.api.utils.UserUtils;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebFilter(filterName = "tokenUserFilter",urlPatterns = "/")
public class BusinessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //获取头信息
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse response1= (HttpServletResponse) response;

        String tokenUser = CookieUtils.getCookieValue(req, "tokenUser");

        //存到公共区域
        UserUtils.setTokenName(tokenUser);
    }
}
