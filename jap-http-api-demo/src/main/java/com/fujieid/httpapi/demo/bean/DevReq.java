package com.fujieid.httpapi.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DevReq implements Serializable {
    private String authSchema;
    private String httpMethod;
    private String loginUrl;
    private String authInfoField;
    private Map<String,String> customHeaders;
    private Map<String,String> customParams;
    private Map<String,String> customBody;
    private String forBearerTokenEnum;
    private String bearerTokenIssueUrl;
}
