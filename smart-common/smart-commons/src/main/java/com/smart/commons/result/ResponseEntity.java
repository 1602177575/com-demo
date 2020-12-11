package com.smart.commons.result;

import com.smart.commons.exception.StatusCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseEntity<T> {
    //状态码
    private int status;
    //描述状态信息
    private String message;
    //数据
    private T data;


    public static <T> ResponseEntity<T> success(T data) {
        return ResponseEntity.<T>builder()
                .status(StatusCode.SUCCESS.getStatus())
                .message(StatusCode.SUCCESS.getMessage())
                .data(data)
                .build();
    }


    public static <T>ResponseEntity<T> success(StatusCode statusCode, T data) {
        return ResponseEntity.<T>builder()
                .status(statusCode.getStatus())
                .message(statusCode.getMessage())
                .data(data)
                .build();
    }

    public static <T> ResponseEntity<T> error() {
        return ResponseEntity.<T>builder()
                .status(StatusCode.ERROR.getStatus())
                .message(StatusCode.ERROR.getMessage())
                .build();
    }

    public static <T> ResponseEntity<T> error(StatusCode statusCode) {
        return ResponseEntity.<T>builder()
                .status(statusCode.getStatus())
                .message(statusCode.getMessage())
                .build();
    }


}
