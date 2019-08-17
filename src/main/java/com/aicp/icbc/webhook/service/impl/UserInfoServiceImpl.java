package com.aicp.icbc.webhook.service.impl;

import com.aicp.icbc.webhook.dao.UserInfoExcelDao;
import com.aicp.icbc.webhook.dto.UserInfoDto;
import com.aicp.icbc.webhook.service.BusinessService;
import com.aicp.icbc.webhook.utils.CommonUtils;
import com.aicp.icbc.webhook.utils.FilterSetterUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @DESC:核身流程server
 */
@Service("UserInfoService")
@Slf4j
public class UserInfoServiceImpl implements BusinessService {
    @Autowired
    UserInfoExcelDao userInfoExcelDao;


    @Override
    public boolean isServiceBeCalled(Map<String, Object> requestMap) {
        if (CommonUtils.isMapEmpty(requestMap)) {
            return false;
        }
        String action = (String) requestMap.get("action");
        if (StringUtils.isEmpty(action)) {
            return false;
        }
        if ("getUserByPhoneNumber".equals(action)) {
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> getResult(Map<String, Object> requestMap) {
        Map<String, Object> requestContext = (Map<String, Object>) requestMap.get("context");
        Map<String, Object> data = new HashMap<>();

        //获取全部Excel中的记录
        List<UserInfoDto> allUserInfoList = userInfoExcelDao.getAllUserInfoList();

        //判断传入的内容是否匹配查询的结果值
        FilterSetterUtil<UserInfoDto> filterSetterUtil = new FilterSetterUtil<>();
        List<UserInfoDto> resultList = filterSetterUtil.getMatchList(requestContext,allUserInfoList);

        //当匹配值不空时
        if (resultList.size() > 0) {
            data.put("userName",resultList.get(0).getUserName());
            //将返回的对象进行key-value赋值
            Map<String, Object> responseContext = filterSetterUtil.setContextValue(resultList.get(0));

            //设值返回标志字段
            Map<String,Object> childMap = new HashMap<>();
            childMap.put("sex",resultList.get(0).getSex());
            responseContext.put("cardNumber",resultList.get(0).getCardNumber());
            responseContext.put("api_response_status", true);
            responseContext.put("size",resultList.size());
            requestContext.put("childMap",childMap);
            data.put("context", responseContext);
        } else {
            Map<String, Object> responseContext = new HashMap<>();
            responseContext.put("api_response_status", false);
            data.put("context", responseContext);
        }
        return data;
    }

}
