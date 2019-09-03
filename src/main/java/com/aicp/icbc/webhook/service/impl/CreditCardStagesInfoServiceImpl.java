package com.aicp.icbc.webhook.service.impl;


import com.aicp.icbc.webhook.dao.CreditCardStagesInfoExcelDao;
import com.aicp.icbc.webhook.dto.CreditCardStagesInfoDto;
import com.aicp.icbc.webhook.service.BusinessService;
import com.aicp.icbc.webhook.utils.CommonUtils;
import com.aicp.icbc.webhook.utils.FilterSetterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @DESC: 信用卡分期外呼流程server
 */
@Service("CreditCardStagesInfoService")
@Slf4j
public class CreditCardStagesInfoServiceImpl implements BusinessService {
    @Autowired
    private CreditCardStagesInfoExcelDao creditCardStagesInfoExcelDao;

    public final String ACTION_GET_CREDIT_STAGES = "getCreditStages";

    public final String ACTION_GET_CREDIT_NUM_STAGES = "getCreditNumStages";

    public final String ACTION_GET_CREDIT_NUM_STAGES2 = "getCreditNumStages2";

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
        //判断请求的action类别
        String action = (String) requestMap.get("action");
        //1、信用卡分期外呼-核身
        if(this.ACTION_GET_CREDIT_STAGES.equals(action)){
            return this.getCreditStagesResult(requestContext);
        }

        //2、信用卡分期外呼-办理期数
        if(this.ACTION_GET_CREDIT_NUM_STAGES.equals(action)){
            return this.getCreditNumStagesResult(requestContext);
        }

        //3、信用卡分期外呼-确定办理期数
        if(this.ACTION_GET_CREDIT_NUM_STAGES2.equals(action)){
            return this.getCreditNumStagesResult(requestContext);
        }


        return new HashMap<>();
    }

    /**
     * 1、信用卡分期外呼-核身
     * @param requestContext
     * @return
     */
    private Map<String, Object> getCreditStagesResult(Map<String, Object> requestContext){
        Map<String, Object> data = new HashMap<>();

        //获取全部Excel中的记录
        List<CreditCardStagesInfoDto> allInfoList = creditCardStagesInfoExcelDao.getAllInfoList();

        //判断传入的内容是否匹配查询的结果值
        FilterSetterUtil<CreditCardStagesInfoDto> filterSetterUtil = new FilterSetterUtil<>();
        //设置本次节点所需要的键（入参变量）
        List<String> goalKeys = Arrays.asList("phoneNumber");
        List<CreditCardStagesInfoDto> resultList = filterSetterUtil.getMatchList(requestContext, allInfoList, goalKeys);
        //当匹配到值时,
        if (resultList.size() > 0) {
            //将返回的对象进行key-value赋值
            Map<String, Object> responseContext = filterSetterUtil.setContextValue(resultList.get(0));
            responseContext.put("recordFlag", "Y");

            //设值返回标志字段
            responseContext.put("api_response_msg", "匹配数据成功");
            responseContext.put("api_response_status", true);
            data.put("context", responseContext);
        } else if (resultList.size()  == 0 ){
            //当未匹配到值时
            Map<String, Object> responseContext = new HashMap<>();
            responseContext.put("recordFlag", "N");

            responseContext.put("api_response_msg", "无法匹配到记录");
            responseContext.put("api_response_status", true);
            data.put("context", responseContext);
        }
        return data;
    }


    /**
     * 2、信用卡分期外呼-办理期数
     * @param requestContext
     * @return
     */
    private Map<String, Object> getCreditNumStagesResult(Map<String, Object> requestContext){
        Map<String, Object> data = new HashMap<>();
        //获取全部Excel中的记录
        List<CreditCardStagesInfoDto> allInfoList = creditCardStagesInfoExcelDao.getAllInfoList();

        //判断传入的内容是否匹配查询的结果值
        FilterSetterUtil<CreditCardStagesInfoDto> filterSetterUtil = new FilterSetterUtil<>();
        //设置本次节点所需要的键（入参变量）
        List<String> goalKeys = Arrays.asList("userName","numberStages");
        List<CreditCardStagesInfoDto> resultList = filterSetterUtil.getMatchList(requestContext, allInfoList, goalKeys);

        //获取分期数
        String numberStages = (String) requestContext.get("numberStages");

        if (resultList.size() == 1) {
            //当匹配到唯一值时,
            //将返回的对象进行key-value赋值
            CreditCardStagesInfoDto result = this.getStagesInfoByNumStages(resultList.get(0),numberStages);
            Map<String, Object> responseContext = filterSetterUtil.setContextValue(result);

            //设值返回标志字段
            responseContext.put("api_response_msg", "匹配数据成功");
            responseContext.put("api_response_status", true);
            data.put("context", responseContext);
        }else if (resultList.size() == 0 ){
            //当匹配不到值时
            Map<String, Object> responseContext = new HashMap<>();
            responseContext.put("responseDataSize", resultList.size());

            //设值返回标志字段
            responseContext.put("api_response_status", true);
            responseContext.put("api_response_msg", "无法匹配到记录");
            data.put("context", responseContext);
        }
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

    /***
     * 通过反射，判断所选分期数的信息，并赋值
     * @param dto
     * @param numberStages
     * @return
     */
    private CreditCardStagesInfoDto getStagesInfoByNumStages(CreditCardStagesInfoDto dto, String numberStages){
        CreditCardStagesInfoDto result = new CreditCardStagesInfoDto();
        //获取两个字段集
        Field[] fields = dto.getClass().getDeclaredFields();
        Field[] fieldsSetvalue = result.getClass().getDeclaredFields();

        //判断出
        for (Field perField:fields) {
            perField.setAccessible(true);
            if(perField.getName().indexOf(numberStages, perField.getName().length() - 2) > 0){
                for (Field setValueField:fieldsSetvalue) {
                    setValueField.setAccessible(true);
                    try {
                        //对四个非表字段进行赋值 --- 传入的期数为1位数
                        if(perField.getName().substring(0,perField.getName().length() -1).matches(setValueField.getName())){
                            setValueField.set(result,perField.get(dto));
                        }
                        //对四个非表字段进行赋值 --- 传入的期数为2位数
                        if(perField.getName().substring(0,perField.getName().length() -2).matches(setValueField.getName())){
                            setValueField.set(result,perField.get(dto));
                        }
                    }catch (IllegalAccessException e){
                        log.error("无法访问字段");
                    }
                }
            };
        }
        result.setInstalment(dto.getInstalment());
        return result;
    }

}
