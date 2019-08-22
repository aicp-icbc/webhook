package com.aicp.icbc.webhook.controller;

import com.aicp.icbc.webhook.service.BusinessService;
import com.aicp.icbc.webhook.utils.RequestUtils;
import com.aicp.icbc.webhook.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 基础controller写固定流程
 * @Author: 吴开云
 * @Date: 2019/8/20 0020
 * @Version： 1.0
 */
@RestController
@Slf4j
public class BaseWebHookController {

    /**
     * 信息处理流程，不同的流程加载不同的businessService
     * 不同的流程设置不同的RequestMapping
     * @param requestBody
     * @return
     * @throws IOException
     */
    public String getInfo(String requestBody, BusinessService businessService) throws IOException{
        // 解析查询请求
        Map<String, Object> request = RequestUtils.getRequest(requestBody);

        //进行业务判断,这里判断本请求是否为该会话中涉及的action
        if (businessService.isServiceBeCalled(request)) {
            Map<String, Object> resultData = businessService.getResult(request);
            return ResponseUtil.usccess(resultData);
        }

        // 没有业务被调用,根据实际需要进行返回,以下只提供参考
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> responseContext = new HashMap<>();
        String value = (String) request.get("value");

        responseContext.put("api_response_msg", "请求匹配action失败");
        responseContext.put("api_response_status", false);
        data.put("context", responseContext);
        if (StringUtils.isNotEmpty(value)) {
            // 当前节点中配置的value，如果webhook异常将这个话术返回给用户
            data.put("value", value);
        }

        return ResponseUtil.serverNotMatch(data);
    }
}
