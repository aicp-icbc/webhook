
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 账单分期流程-controller
 * @Author: 吴开云
 * @Date: 2019/8/19 0019
 * @Version： 1.0
 */
@RequestMapping(value = "/api/v1/webhook")
@RestController
public class StagingWebHookController extends BaseWebHookController{

    @Autowired
    @Qualifier("StagingInfoService")
    BusinessService businessService;

    /**
     * 查询核身流程中的用户信息-带访问路径
     * @param requestBody
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/staging", method = RequestMethod.POST)
    public String getInfo(@RequestBody String requestBody) throws IOException{
        return super.getInfo(requestBody,businessService);
    }

}
