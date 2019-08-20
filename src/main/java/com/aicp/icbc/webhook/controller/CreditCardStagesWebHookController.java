
package com.aicp.icbc.webhook.controller;

import com.aicp.icbc.webhook.service.BusinessService;
import com.aicp.icbc.webhook.utils.RequestUtils;
import com.aicp.icbc.webhook.utils.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
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
 * @Description: 吞卡流程-controller
 * @Author: 吴开云
 * @Date: 2019/8/19 0019
 * @Version： 1.0
 */
public class CreditCardStagesWebHookController extends BaseWebHookController{

    @Autowired
    @Qualifier("CreditCardStagesInfoService")
    BusinessService businessService;

    /**
     * 吞卡流程
     * @param requestBody
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/creditcardstages", method = RequestMethod.POST)
    public String getInfo(@RequestBody String requestBody) throws IOException{
        return super.getInfo(requestBody,businessService);
    }

}
