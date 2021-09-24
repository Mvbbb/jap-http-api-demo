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
            httpApiConfig.setHttpMethod(HttpMethodEnum.POST);
        }else{
            httpApiConfig.setHttpMethod(HttpMethodEnum.GET);
        }
        if(StringUtils.equalsIgnoreCase(devReq.getAuthSchema(),"basic")){
            httpApiConfig.setAuthSchema(AuthSchemaEnum.BASIC);
        }else if(StringUtils.equalsIgnoreCase(devReq.getAuthSchema(),"digest")){
            httpApiConfig.setAuthSchema(AuthSchemaEnum.DIGEST);
        }else{
            httpApiConfig.setAuthSchema(AuthSchemaEnum.BEARER);
            httpApiConfig.setBearerTokenIssueUrl(devReq.getBearerTokenIssueUrl());
            switch (devReq.getForBearerTokenEnum()){
                case "by_header":
                    httpApiConfig.setForBearerTokenEnum(ForBearerTokenEnum.BY_HEADER);
                    break;
                case "by_params":
                    httpApiConfig.setForBearerTokenEnum(ForBearerTokenEnum.BY_PARAMS);
                    break;
                case "by_body":
                    httpApiConfig.setForBearerTokenEnum(ForBearerTokenEnum.BY_BODY);
                    break;
                case "by_basic":
                    httpApiConfig.setForBearerTokenEnum(ForBearerTokenEnum.BY_BASIC);
                    break;
                case "by_digest":
                    httpApiConfig.setForBearerTokenEnum(ForBearerTokenEnum.BY_DIGEST);
                    break;
                default:
                    break;
            }
        }
        httpApiConfig.setLoginUrl(devReq.getLoginUrl().trim()) ;
        if(StringUtils.equalsIgnoreCase(devReq.getAuthInfoField(),"header")){
            httpApiConfig.setAuthInfoField(AuthInfoFieldEnum.HEADER);
        }else if(StringUtils.equalsIgnoreCase(devReq.getAuthInfoField(),"params")){
            httpApiConfig.setAuthInfoField(AuthInfoFieldEnum.PARAMS);
        }else{
            httpApiConfig.setAuthInfoField(AuthInfoFieldEnum.BODY);
        }
        httpApiConfig.setCustomHeaders(devReq.getCustomHeaders());
        httpApiConfig.setCustomParams(devReq.getCustomParams());
        httpApiConfig.setCustomBody(devReq.getCustomBody());
        return httpApiConfig;
    }
}
