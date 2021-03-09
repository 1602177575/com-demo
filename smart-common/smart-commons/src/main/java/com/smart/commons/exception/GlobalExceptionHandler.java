package com.smart.commons.exception;


import com.smart.commons.result.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * 全局捕捉异常
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handler(Exception exception) {
        // 判断异常类型
        if (exception instanceof ServiceException) {
            //状态码
            return ResponseEntity.error(StatusCode.SERVER_ERROR);
        } else if(exception instanceof SQLException){
            return ResponseEntity.error(StatusCode.SERVER_ERROR);
        }else {
            return ResponseEntity.error(StatusCode.ERROR);
        }
    }
}
