package com.smart.sec.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.sec.Response.ResponseResult;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 返回结果 JSON封装
 */
@Component
public class ResponseUtils {


    public static void responseToJson(HttpServletResponse response, ResponseResult<Object> responseResult) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(responseResult));
    }

    public static void responseToJson(HttpServletResponse response,ObjectMapper obj, ResponseResult<Object> responseResult) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(obj.writeValueAsString(responseResult));
    }

}
