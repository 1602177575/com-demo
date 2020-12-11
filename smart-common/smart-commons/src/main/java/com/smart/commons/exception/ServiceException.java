package com.smart.commons.exception;

import lombok.Getter;

@Getter
public class ServiceException extends BaseException {
    public ServiceException(StatusCode statusCode) {
        super(statusCode);
    }
}
