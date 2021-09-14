package com.fujieid.httpapi.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.fujieid.httpapi.demo.bean.DevReq;
import com.fujieid.httpapi.demo.common.RestResult;
import com.fujieid.httpapi.demo.service.JapHttpApiUserServiceImpl;
import com.fujieid.httpapi.demo.util.BeanConvertUtil;
import com.fujieid.jap.core.cache.JapLocalCache;
import com.fujieid.jap.core.config.JapConfig;
import com.fujieid.jap.core.result.JapResponse;
import com.fujieid.jap.httpapi.HttpApiConfig;
import com.fujieid.jap.httpapi.HttpApiStrategy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api",produces = "application/json;charset=utf-8")
public class IndexController {

    HttpApiStrategy httpApiStrategy = new HttpApiStrategy(new JapHttpApiUserServiceImpl(), new JapConfig(),new JapLocalCache());
    HttpApiConfig httpApiConfig;


    @RequestMapping("/login")
    public RestResult login(HttpServletRequest request, HttpServletResponse response){
        if(httpApiConfig==null){
            return RestResult.failed("connect to developer to init this system");
        }
        JapResponse result = httpApiStrategy.authenticate(httpApiConfig, request, response);
        if(result.getCode()==200){
            return RestResult.success("login success", JSONObject.toJSONString(result));
        }else{
            return RestResult.failed("login failed",JSONObject.toJSONString(result));
        }
    }

    @RequestMapping("/set")
    public RestResult setDev(@RequestBody DevReq devReq){
        httpApiConfig = BeanConvertUtil.toHttpApiConfig(devReq);
        if(httpApiConfig==null){
            return RestResult.failed("Incomplete parameters");
        }else{
            return RestResult.success("Update success", JSONObject.toJSONString(devReq));

        }
    }
}
