/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.aicp.icbc.webhook.controller;

import com.aicp.icbc.webhook.service.BusinessService;
import com.aicp.icbc.webhook.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;


@RequestMapping(value = "/api/v1/webhook")
@RestController
@Slf4j
public class WebHookController extends BaseWebHookController{

    private static Logger logger = LoggerFactory.getLogger(WebHookController.class);

    @Autowired
    @Qualifier("BalanceMindInfoService")
    BusinessService balanceMindInfoService;

    @Autowired()
    @Qualifier("CreditCardCollectionService")
    BusinessService creditCardCollectionService;

    @Autowired
    @Qualifier("CreditCardStagesInfoService")
    BusinessService creditCardStagesInfoService;

    @Autowired
    @Qualifier("StagingInfoService")
    BusinessService stagingInfoService;

    @Autowired
    @Qualifier("SwallowCardInfoService")
    BusinessService swallowCardInfoService;

    @Autowired
    @Qualifier("AdjustmentInfoService")
    BusinessService adjustmentInfoService;

    @Autowired
    @Qualifier("HelperInfoService")
    BusinessService helperInfoService;

    @Autowired
    @Qualifier("BankCardInfoService")
    BusinessService bankCardInfoService;

    /**
     * 获取返回值结果
     * @param requestBody
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String getInfo(@RequestBody String requestBody) throws IOException{
        BusinessService businessService = checkService(requestBody);
        return super.getInfo(requestBody,businessService);
    }


    /**
     * 根据action判定所使用的服务
     * @param requestBody
     * @return
     * @throws IOException
     */
    private BusinessService checkService(String requestBody) throws IOException{
        //获取request的key-value map
        Map<String, Object> request = RequestUtils.getRequest(requestBody);
        //获取定义的service对象
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field perField : fields) {
            perField.setAccessible(true);
            try {
                //判断能够进行类型强转
                if(perField.get(this) instanceof BusinessService){
                    BusinessService businessService = (BusinessService) perField.get(this);
                    //判断action所属的service，并获取返回值
                    if (businessService.isServiceBeCalled(request)){
                        return businessService;
                    }
                }
            }catch (IllegalAccessException e){
                log.error("字段无访问权限");
            }

        }

        //无法匹配则返回收不到短信提醒流程service
        return this.balanceMindInfoService;
    }
}
