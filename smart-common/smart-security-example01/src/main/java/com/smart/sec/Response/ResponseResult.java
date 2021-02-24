package com.smart.sec.Response;

import lombok.Data;

@Data
public class ResponseResult<T> {
    private int status;
    private String msg;
    private T date;


    public static <T> ResponseResult<T> success(T date) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setStatus(200);
        responseResult.setMsg("success");
        responseResult.setDate(date);
        return  responseResult;
    }

    public static <T> ResponseResult<T> error(R r){
        ResponseResult<T> result = new ResponseResult<>();
        result.setStatus(r.getStatus());
        result.setMsg(r.getMsg());
        return result;
    }

    public static <T> ResponseResult<T> error(String msg){
        ResponseResult<T> result = new ResponseResult<>();
        result.setStatus(600);
        result.setMsg(msg);
        return result;
    }

    public static <T> ResponseResult<T> error(){
        ResponseResult<T> result = new ResponseResult<>();
        result.setStatus(600);
        result.setMsg("error");
        return result;
    }




}
