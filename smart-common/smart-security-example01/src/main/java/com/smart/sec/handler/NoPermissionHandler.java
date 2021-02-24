package com.smart.sec.handler;

import com.smart.sec.Response.R;
import com.smart.sec.Response.ResponseResult;
import com.smart.sec.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 无权访问处理
 */


@Component
@Slf4j
public class NoPermissionHandler implements AccessDeniedHandler {

    @Resource
    ResponseUtils responseUtils;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {

        responseUtils.responseToJson(response, ResponseResult.error(R.NO_PERMISSION));
    }
}
