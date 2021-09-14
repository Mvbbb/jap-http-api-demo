package com.fujieid.httpapi.demo.common;

import lombok.Data;

@Data
public class RestResult {

    private Integer code;
    private String message;
    private Object data;

    public static RestResult success(String message,Object obj) {
        RestResult restResult = new RestResult();
        restResult.setCode(200);
        restResult.setMessage(message);
        restResult.setData(obj);
        return restResult;

    }

    public static RestResult failed(String message,Object obj){
        RestResult restResult = new RestResult();
        restResult.setCode(400);
        restResult.setMessage(message);
        restResult.setData(obj);
        return restResult;
    }

    public static RestResult failed(String message){
        return failed(message,null);
    }
}
