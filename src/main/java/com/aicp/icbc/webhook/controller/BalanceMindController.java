package com.aicp.icbc.webhook.controller;

import com.aicp.icbc.webhook.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Description: 收不到余额变动提醒 -- controller
 * @Author: 吴开云
 * @Date: 2019/8/21 0021
 * @Version： 1.0
 */
@RequestMapping(value = "/api/v1/webhook")
@RestController
public class BalanceMindController extends BaseWebHookController{
    @Autowired
    @Qualifier("BalanceMindInfoService")
    BusinessService businessService;


    @RequestMapping(value = "/balancemind", method = RequestMethod.POST)
    public String getInfo(@RequestBody String requestBody) throws IOException{
        return super.getInfo(requestBody,businessService);
    }
}
