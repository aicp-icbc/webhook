
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
@RequestMapping(value = "/api/v1/webhook")
@RestController
public class SwallowCardWebHookController {

    @Autowired
    @Qualifier("SwallowCardInfoService")
    BusinessService swallowCardInfoService;

    /**
     * 吞卡流程
     * @param requestBody
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/swallowcard", method = RequestMethod.POST)
    public String getUserByPhoneNumberWithUri(@RequestBody String requestBody) throws IOException{
        // 解析查询请求
        Map<String, Object> request = RequestUtils.getRequest(requestBody);

        //进行业务判断,这里判断本请求是否为该会话中涉及的action
        if (swallowCardInfoService.isServiceBeCalled(request)) {
            Map<String, Object> resultData = swallowCardInfoService.getResult(request);
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
