package com.fujieid.httpapi.demo.util;

import com.fujieid.httpapi.demo.bean.DevReq;
import com.fujieid.jap.httpapi.HttpApiConfig;
import com.fujieid.jap.httpapi.enums.AuthInfoFieldEnum;
import com.fujieid.jap.httpapi.enums.AuthSchemaEnum;
import com.fujieid.jap.httpapi.enums.ForBearerTokenEnum;
import com.fujieid.jap.httpapi.enums.HttpMethodEnum;
import org.apache.commons.lang.StringUtils;


public class BeanConvertUtil {
    public static HttpApiConfig toHttpApiConfig(DevReq devReq){

        if (StringUtils.isEmpty(devReq.getHttpMethod())
                ||StringUtils.isEmpty(devReq.getAuthSchema())
                ||StringUtils.isEmpty(devReq.getAuthInfoField())
                ||StringUtils.isEmpty(devReq.getLoginUrl())) {
            return null;
        }

        HttpApiConfig httpApiConfig = new HttpApiConfig();
        if (StringUtils.equalsIgnoreCase(devReq.getHttpMethod(),"post")) {
            httpApiConfig.setHttpMethod(HttpMethodEnum.post);
        }else{
            httpApiConfig.setHttpMethod(HttpMethodEnum.get);
        }
        if(StringUtils.equalsIgnoreCase(devReq.getAuthSchema(),"basic")){
            httpApiConfig.setAuthSchema(AuthSchemaEnum.basic);
        }else if(StringUtils.equalsIgnoreCase(devReq.getAuthSchema(),"digest")){
            httpApiConfig.setAuthSchema(AuthSchemaEnum.digest);
        }else{
            httpApiConfig.setAuthSchema(AuthSchemaEnum.bearer);
            httpApiConfig.setBearerTokenIssueUrl(devReq.getBearerTokenIssueUrl());
            switch (devReq.getForBearerTokenEnum()){
                case "by_header":
                    httpApiConfig.setForBearerTokenEnum(ForBearerTokenEnum.by_header);
                    break;
                case "by_params":
                    httpApiConfig.setForBearerTokenEnum(ForBearerTokenEnum.by_params);
                    break;
                case "by_body":
                    httpApiConfig.setForBearerTokenEnum(ForBearerTokenEnum.by_body);
                    break;
                case "by_basic":
                    httpApiConfig.setForBearerTokenEnum(ForBearerTokenEnum.by_basic);
                    break;
                case "by_digest":
                    httpApiConfig.setForBearerTokenEnum(ForBearerTokenEnum.by_digest);
                    break;
                default:
                    break;
            }
        }
        httpApiConfig.setLoginUrl(devReq.getLoginUrl().trim()) ;
        if(StringUtils.equalsIgnoreCase(devReq.getAuthInfoField(),"header")){
            httpApiConfig.setAuthInfoField(AuthInfoFieldEnum.header);
        }else if(StringUtils.equalsIgnoreCase(devReq.getAuthInfoField(),"params")){
            httpApiConfig.setAuthInfoField(AuthInfoFieldEnum.params);
        }else{
            httpApiConfig.setAuthInfoField(AuthInfoFieldEnum.body);
        }
        httpApiConfig.setCustomHeaders(devReq.getCustomHeaders());
        httpApiConfig.setCustomParams(devReq.getCustomParams());
        httpApiConfig.setCustomBody(devReq.getCustomBody());
        return httpApiConfig;
    }
}
