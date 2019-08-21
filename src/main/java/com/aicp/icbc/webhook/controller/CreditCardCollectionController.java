/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.aicp.icbc.webhook.controller;

import com.aicp.icbc.webhook.rest.ApiResponse;
import com.aicp.icbc.webhook.service.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Description: 信用卡催收外呼流程-controller
 * @Author: 吴开云
 * @Date: 2019/8/19 0019
 * @Version： 1.0
 */
@RequestMapping(value = "/api/v1/webhook")
@RestController
public class CreditCardCollectionController extends BaseWebHookController{

    private static Logger logger = LoggerFactory.getLogger(CreditCardCollectionController.class);
    private ApiResponse apiResponse = ApiResponse.getInstance();

    @Autowired()
    @Qualifier("CreditCardCollectionService")
    BusinessService businessService;


    @RequestMapping(value = "/verifyIdentityInfo", method = RequestMethod.POST)
    public String getInfo(@RequestBody String requestBody) throws IOException{
        return super.getInfo(requestBody,businessService);
    }



}
