package com.aicp.icbc.webhook.service.impl;

import com.aicp.icbc.webhook.dao.UserInfoExcelDao;
import com.aicp.icbc.webhook.dto.UserInfoDto;
import com.aicp.icbc.webhook.service.BusinessService;
import com.aicp.icbc.webhook.utils.CommonUtils;
import com.aicp.icbc.webhook.utils.ValueFilteUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
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
        Map<String, Object> context = (Map<String, Object>) requestMap.get("context");
        String phoneNumber = (String) context.get("phoneNumber");
        List<UserInfoDto> allUserInfoList = userInfoExcelDao.getAllUserInfoList();
        Map<String, Object> data = new HashMap<>();
        //判断传入的内容是否匹配查询的结果值
        ValueFilteUtil<UserInfoDto> valueFilteUtil = new ValueFilteUtil<>();
        List<UserInfoDto> resultList = valueFilteUtil.getMatchList(context,allUserInfoList);
        //当匹配值不空时
        if (resultList.size() > 0) {
            JSONArray jsonArray = new JSONArray();
            for (UserInfoDto perDto:resultList) {
                if(phoneNumber.equals(perDto.getPhoneNumber())){
                    jsonArray.add(JSON.toJSONString(perDto));
                }
            }
            data.put("userName",resultList.get(0).getUserName());
            Map<String, Object> returnContext = new HashMap<>();
            Map<String,Object> childMap = new HashMap<>();
            childMap.put("sex",resultList.get(0).getSex());
            returnContext.put("jsonArray", jsonArray);
            returnContext.put("cardNumber",resultList.get(0).getCardNumber());
            returnContext.put("api_response_status", true);
            returnContext.put("size",resultList.size());
            data.put("context", returnContext);
        } else {
            Map<String, Object> returnContext = new HashMap<>();
            returnContext.put("api_response_status", false);
            data.put("context", returnContext);
        }
        return data;
    }

}
