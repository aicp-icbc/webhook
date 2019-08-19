/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.aicp.icbc.webhook.controller;

import com.aicp.icbc.webhook.rest.ApiResponse;
import com.aicp.icbc.webhook.service.BusinessService;
import com.aicp.icbc.webhook.utils.RequestUtils;
import com.aicp.icbc.webhook.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "/api/v1/webhook")
@RestController
public class WebHookController {

    private static Logger logger = LoggerFactory.getLogger(WebHookController.class);
    private ApiResponse apiResponse = ApiResponse.getInstance();

    @Autowired()
    @Qualifier("UserInfoService")
    BusinessService userInfoService;

    /**
     * 查询核身流程中的用户信息-不带访问路径
     * @param requestBody
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String getUserByPhoneNumber(@RequestBody String requestBody) throws IOException{
        // 解析查询请求
        Map<String, Object> request = RequestUtils.getRequest(requestBody);
        //进行业务判断,这里判断是否为查询核身流程中的用户信息
        if (userInfoService.isServiceBeCalled(request)) {
            Map<String, Object> resultData = userInfoService.getResult(request);
            return ResponseUtil.usccess(resultData);
        }

        // 没有业务被调用,根据实际需要进行返回,以下只提供参考
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> returnContext = new HashMap<>();
        String value = (String) request.get("value");
        returnContext.put("api_response_status", false);
        data.put("context", returnContext);
        if (StringUtils.isNotEmpty(value)) {
            // 当前节点中配置的value，如果webhook异常将这个话术返回给用户
            data.put("value", value);
        }

        return ResponseUtil.serverNotMatch(data);
    }

    /**
     * 查询核身流程中的用户信息-带访问路径
     * @param requestBody
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/verifyIdentityInfo", method = RequestMethod.POST)
    public String getUserByPhoneNumberWithUri(@RequestBody String requestBody) throws IOException{
        // 解析查询请求
        Map<String, Object> request = RequestUtils.getRequest(requestBody);
        //进行业务判断,这里判断是否为查询核身流程中的用户信息
        if (userInfoService.isServiceBeCalled(request)) {
            Map<String, Object> resultData = userInfoService.getResult(request);
            return ResponseUtil.usccess(resultData);
        }

        // 没有业务被调用,根据实际需要进行返回,以下只提供参考
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> returnContext = new HashMap<>();
        String value = (String) request.get("value");
        returnContext.put("api_response_status", false);
        data.put("context", returnContext);
        if (StringUtils.isNotEmpty(value)) {
            // 当前节点中配置的value，如果webhook异常将这个话术返回给用户
            data.put("value", value);
        }

        return ResponseUtil.serverNotMatch(data);
    }

}
