package com.smart.security.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.security.dto.ResponseResult;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mtl
 */
public class ResposeUtils {

    public static void responseToJson(HttpServletResponse response, ObjectMapper objectMapper, ResponseResult<Object> result) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(objectMapper.writeValueAsString(result));

    }

}
