package com.aicp.icbc.webhook.service.impl;


import com.aicp.icbc.webhook.dao.AdjustmentInfoExcelDao;
import com.aicp.icbc.webhook.dto.AdjustmentInfoDto;
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
 * @DESC: 一键调额流程server
 */
@Service("AdjustmentInfoService")
@Slf4j
public class AdjustmentInfoServiceImpl implements BusinessService {
    @Autowired
    private AdjustmentInfoExcelDao AdjustmentInfoExcelDao;

    public final String EN_CUSTOMER_AUTHEN_TICATION = "EDcustomerAuthentication";

    public final String ADJUSTMENT_ACTION_CHECK_CARD_RECORD = "adjustmentCheckCardRecord";

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
        //1、吞卡-吞卡记录查询
        if(this.EN_CUSTOMER_AUTHEN_TICATION.equals(action)){
            return this.getCardRecordResult(requestContext);
        }

        //2、吞卡-吞卡记录核对
        if(this.ADJUSTMENT_ACTION_CHECK_CARD_RECORD.equals(action)){
            return this.getPasswordValidationResult(requestContext);
        }


        return new HashMap<>();
    }

    /**
     * 1、吞卡-吞卡记录查询
     *是否存在吞卡记录（Y/N）
     * 如果主叫号码不存在记录，则返回N
     * @param requestContext
     * @return
     */
    private Map<String, Object> getCardRecordResult(Map<String, Object> requestContext){
        Map<String, Object> data = new HashMap<>();

        //获取全部Excel中的记录
        List<AdjustmentInfoDto> allInfoList = AdjustmentInfoExcelDao.getAllInfoList();


        //判断传入的内容是否匹配查询的结果值
        FilterSetterUtil<AdjustmentInfoDto> filterSetterUtil = new FilterSetterUtil<>();
        //设置本次节点所需要的键（入参变量）
        List<String> goalKeys = Arrays.asList("cardNum");
        List<AdjustmentInfoDto> resultList = filterSetterUtil.getMatchList(requestContext, allInfoList, goalKeys);
        //当匹配到值时,
        if (resultList.size() > 0) {
            //将返回的对象进行key-value赋值
            Map<String, Object> responseContext = filterSetterUtil.setContextValue(resultList.get(0));
            responseContext.put("recordFlag", "Y");

            //设值返回标志字段
            responseContext.put("api_response_msg", "匹配数据成功");
            data.put("context", responseContext);
        } else if (resultList.size()  == 0 ){
            //当未匹配到值时
            Map<String, Object> responseContext = new HashMap<>();
            responseContext.put("recordFlag", "N");

            responseContext.put("api_response_msg", "无法匹配到记录");
            data.put("context", responseContext);
        }
        return data;
    }


    /**
     * 2、吞卡-吞卡记录核对
     *卡号,卡号后四位,吞卡时间,吞卡位置,四个参数至少存在一个
     * @param requestContext
     * @return
     */
    private Map<String, Object> getPasswordValidationResult(Map<String, Object> requestContext){
        Map<String, Object> data = new HashMap<>();
        //获取全部Excel中的记录
        List<AdjustmentInfoDto> allInfoList = AdjustmentInfoExcelDao.getAllInfoList();

        //判断传入的内容是否匹配查询的结果值
        FilterSetterUtil<AdjustmentInfoDto> filterSetterUtil = new FilterSetterUtil<>();

        //吞卡记录可能会传入一个其它的电话号码
        String queryTelephone = (String)requestContext.get("queryTelephone");
        //当传入的其它号码不空时，进行替换
        if(!StringUtils.isEmpty(queryTelephone)){
            requestContext.remove("queryTelephone");
            requestContext.put("phoneNumber",queryTelephone);
        }

        List<AdjustmentInfoDto> resultList = filterSetterUtil.getMatchList(requestContext, allInfoList);

        if (resultList.size() == 1) {
            //当匹配到唯一值时,
            //将返回的对象进行key-value赋值
            Map<String, Object> responseContext = filterSetterUtil.setContextValue(resultList.get(0));
            responseContext.put("responseDataSize", resultList.size());

            //设值返回标志字段
            responseContext.put("api_response_msg", "匹配数据成功");
            responseContext.put("api_response_status", true);
            data.put("context", responseContext);
        } else if (resultList.size()  > 1 ){
            //当匹配到多个值时
            Map<String, Object> responseContext = new HashMap<>();

            //将每个值的键加上index后缀
            for (Integer i = 0; i < resultList.size() ; i ++) {
                AdjustmentInfoDto perDto = resultList.get(i);
                String indexStr = i == 0 ? "" : i.toString();
                Map<String, Object> perContext = new HashMap<>();

                //取所有定义字段
                Field[] fields = perDto.getClass().getDeclaredFields();

                //判断字段值是否为空
                for (Field perField:fields) {
                    perField.setAccessible(true);
                    try {
                        if(!StringUtils.isEmpty(perField.get(perDto))){
                            //取非空字段进行 key，value赋值
                            perContext.put(perField.getName() + indexStr, perField.get(perDto));
                        }
                    }catch (IllegalAccessException e){
                        e.printStackTrace();
                        log.error("无法访问字段");
                    }
                }
                //将所有的key，value值放进返回的context中
                responseContext.putAll(perContext);
            }
            responseContext.put("responseDataSize", resultList.size());

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

}
