package com.smart.commons.exception;

import lombok.Getter;

/**
 * 状态码泛型
 */
@Getter
public enum StatusCode {
    SUCCESS(200, "success"),
    ERROR(404, "error"),
    PN_ERROR(666,"账号或者密码错误，请重试"),
    SYS_ERROR(4000, "系统错误!!! 请稍后再试"),
    EMAIL_ERROR(4001,"邮箱错误请重新输入"),
    REGISTER_ERROR(4008,"用户名重复，注册失败，请重试"),
    KEY_ERROR(4009,"输入违规字符,请重试"),
    SERVER_ERROR(5000, "服务器错误");
    private int status;
    private String message;

    StatusCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
