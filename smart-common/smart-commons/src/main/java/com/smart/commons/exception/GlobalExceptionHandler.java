package com.smart.commons.exception;


import com.smart.commons.result.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handler(Exception exception) {
        if (exception instanceof ServiceException) {
            return ResponseEntity.error(StatusCode.SERVER_ERROR);
        } else {
            return ResponseEntity.error(StatusCode.ERROR);
        }
    }
}
