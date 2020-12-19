package com.smart.security.dto;

import lombok.Data;

/**
 * @author mtl
 */
@Data
public class ResponseResult<T> {
    private int status;
    private String msg;
    private T date;

    public static <T> ResponseResult<T> success(T date){
        ResponseResult<T> result=new ResponseResult<>();
        result.setStatus(200);
        result.setMsg("success");
        result.setDate(date);
        return result;
    }


}
