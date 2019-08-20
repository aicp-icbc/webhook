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
public class WebHookController extends BaseWebHookController{

    private static Logger logger = LoggerFactory.getLogger(WebHookController.class);
    private ApiResponse apiResponse = ApiResponse.getInstance();

    @Autowired()
    @Qualifier("UserInfoService")
    BusinessService businessService;

    /**
     * 查询核身流程中的用户信息
     * @param requestBody
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/verifyIdentityInfo", method = RequestMethod.POST)
    public String getInfo(@RequestBody String requestBody) throws IOException{
        return super.getInfo(requestBody,businessService);
    }

}
