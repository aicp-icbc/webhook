package com.aicp.icbc.webhook.service.impl;

import com.aicp.icbc.webhook.dao.CreditCardCollectionInfoExcelDao;
import com.aicp.icbc.webhook.dto.CreditCardCollectionInfoDto;
import com.aicp.icbc.webhook.service.BusinessService;
import com.aicp.icbc.webhook.utils.CommonUtils;
import com.aicp.icbc.webhook.utils.FilterSetterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @DESC: 信用卡催收外呼流程server
 */
@Service("CreditCardCollectionService")
@Slf4j
public class CreditCardCollectionServiceImpl implements BusinessService {
    @Autowired
    private CreditCardCollectionInfoExcelDao infoExcelDao;

    public final String ACTION_GET_USER_BY_PHONE_NUMBER = "getUserByPhoneNumber";

    @Override
    public boolean isServiceBeCalled(Map<String, Object> requestMap) {
        if (CommonUtils.isMapEmpty(requestMap)) {
            return false;
        }
        String action = (String) requestMap.get("action");
        if (StringUtils.isEmpty(action)) {
            return false;
        }
        if (this.actionContain(action)) {
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> getResult(Map<String, Object> requestMap) {
        Map<String, Object> requestContext = (Map<String, Object>) requestMap.get("context");
        Map<String, Object> data = new HashMap<>();

        //获取全部Excel中的记录
        List<CreditCardCollectionInfoDto> allInfoList = infoExcelDao.getAllUserInfoList();

        //判断传入的内容是否匹配查询的结果值
        FilterSetterUtil<CreditCardCollectionInfoDto> filterSetterUtil = new FilterSetterUtil<>();
        //设置本次节点所需要的键（入参变量）
        List<String> goalKeys = Arrays.asList("phoneNumber");
        List<CreditCardCollectionInfoDto> resultList = filterSetterUtil.getMatchList(requestContext, allInfoList, goalKeys);

        //当匹配值不空时
        if (resultList.size() > 0) {

            //将返回的对象进行key-value赋值
            Map<String, Object> responseContext = filterSetterUtil.setContextValue(resultList.get(0));

            //设置身份证后四位字段值
            String carNumber = resultList.get(0).getCardNumber();
            String cardNumberFour = carNumber.substring(carNumber.length() - 4, carNumber.length());
            responseContext.put("cardNumberFour", cardNumberFour);
            //将男女更改为先生女士
            String sex = resultList.get(0).getSex();
            if("男".equals(sex)){
                responseContext.put("sex","先生");
            }else if("女".equals(sex)){
                responseContext.put("sex","女生");
            }

            //设值返回标志字段
            responseContext.put("api_response_status", true);
            responseContext.put("api_response_msg", "匹配数据成功");
            responseContext.put("size",resultList.size());
            data.put("context", responseContext);
        } else {
            Map<String, Object> responseContext = new HashMap<>();
            responseContext.put("api_response_status", false);
            responseContext.put("api_response_msg", "无法匹配到记录");
            data.put("context", responseContext);
        }
        return data;
    }

    /**
     * 1、信用卡分期外呼-核身
     * @param requestContext
     * @return
     */
    private Map<String, Object> getCreditStagesResult(Map<String, Object> requestContext){
        Map<String, Object> data = new HashMap<>();


        return data;
    }

    /**
     * 通过反射获取字段名，然后判断action是否包含于该服务中
     * @param action
     * @return
     */
    private Boolean actionContain(String action){
        Boolean containFlag = false;

        //获取public的字段名
        Field[] fields = this.getClass().getFields();

        //通过反射进行判断
        for (Field perField:fields) {
            try {
                if (perField.get(this).equals(action)){
                    containFlag = true;
                    return containFlag;
                }
            }catch (IllegalAccessException e){
                log.error("{}字段不可访问", perField.getName());
            }
        }

        return containFlag;
    }

}
