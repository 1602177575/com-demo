package com.smart.commons.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("ALL")
@Data
@NoArgsConstructor
public class BaseException extends RuntimeException {
    private int status;
    private String message;

    public BaseException(StatusCode statusCode) {
        this.status = statusCode.getStatus();
        this.message = statusCode.getMessage();
    }
}