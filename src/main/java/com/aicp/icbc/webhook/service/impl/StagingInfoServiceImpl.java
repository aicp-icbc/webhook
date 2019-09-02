package com.aicp.icbc.webhook.service.impl;

import com.aicp.icbc.webhook.dao.StagingInfoExcelDao;
import com.aicp.icbc.webhook.dto.CreditCardStagesInfoDto;
import com.aicp.icbc.webhook.dto.StagingInfoDto;
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
 * @DESC:账单分期流程server
 */
@Service("StagingInfoService")
@Slf4j
public class StagingInfoServiceImpl implements BusinessService {
    @Autowired
    private StagingInfoExcelDao stagingInfoExcelDao;

    public final String ACTION_CUSTOMER_AUTHENTICATION = "customerAuthentication";

    public final String ACTION_PASSWORD_VALIDATION = "passwordValidation";

    public final String ACTION_GET_OVERDRAFT = "getOverdraft";

    public final String ACTION_GET_CREDIT_RATE = "getCreditRate";


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
        //1、账单分期-客户身份验证
        if(this.ACTION_CUSTOMER_AUTHENTICATION.equals(action)){
            return this.getCustomerAuthenticationResult(requestContext);
        }

        //2、账单分期-卡号or身份证密码验证
        if(this.ACTION_PASSWORD_VALIDATION.equals(action)){
            return this.getPasswordValidationResult(requestContext);
        }

        //3、账单分期-是否欠款
        if(this.ACTION_GET_OVERDRAFT.equals(action)){
            return this.getOverdraftResult(requestContext);
        }

        //4、账单分期-手续费
        if(this.ACTION_GET_CREDIT_RATE.equals(action)){
            return this.getCreditRateResult(requestContext);
        }

        return new HashMap<>();
    }

    /**
     * 1、账单分期-客户身份验证
     *如果主叫号码不存在记录，则返回N, 存在返回Y
     * @param requestContext
     * @return
     */
    private Map<String, Object> getCustomerAuthenticationResult(Map<String, Object> requestContext){
        Map<String, Object> data = new HashMap<>();

        //获取全部Excel中的记录
        List<StagingInfoDto> allInfoList = stagingInfoExcelDao.getAllInfoList();

        //判断传入的内容是否匹配查询的结果值
        FilterSetterUtil<StagingInfoDto> filterSetterUtil = new FilterSetterUtil<>();
        //设置本次节点所需要的键（入参变量）
        List<String> goalKeys = Arrays.asList("userName");
        List<StagingInfoDto> resultList = filterSetterUtil.getMatchList(requestContext, allInfoList, goalKeys);
        //当匹配到值时,
        if (resultList.size() > 0) {
            //将返回的对象进行key-value赋值
            Map<String, Object> responseContext = filterSetterUtil.setContextValue(resultList.get(0));
            //去除instalment字段
            responseContext.remove("instalment");

            //设置查询到记录
            responseContext.put("recordFlag", "Y");

            //设值返回标志字段
            responseContext.put("api_response_msg", "匹配数据成功");
            data.put("context", responseContext);
        } else if (resultList.size()  == 0 ){
            //当未匹配到值时
            Map<String, Object> responseContext = new HashMap<>();
            //设置查询到记录
            responseContext.put("recordFlag", "N");

            responseContext.put("api_response_msg", "无法匹配到记录");
            data.put("context", responseContext);
        }
        return data;
    }


    /**
     * 2、账单分期-卡号or身份证密码验证
     *卡号&身份证号最少有一个
     * 若cardsNumber>1，说明根据用户输入区分定位银行卡，可能用户输入：身份证信息+密码
     * @param requestContext
     * @return
     */
    private Map<String, Object> getPasswordValidationResult(Map<String, Object> requestContext){
        Map<String, Object> data = new HashMap<>();
        //获取全部Excel中的记录
        List<StagingInfoDto> allInfoList = stagingInfoExcelDao.getAllInfoList();

        //判断传入的内容是否匹配查询的结果值
        FilterSetterUtil<StagingInfoDto> filterSetterUtil = new FilterSetterUtil<>();
        List<StagingInfoDto> resultList = filterSetterUtil.getMatchList(requestContext, allInfoList);

        if (resultList.size() == 1) {
            //当匹配到唯一值时,
            //将返回的对象进行key-value赋值
            Map<String, Object> responseContext = new HashMap<>();
            responseContext.put("responseDataSize", resultList.size());
            responseContext.put("passwordMatchFlag", "Y");
            responseContext.put("userName", resultList.get(0).getUserName());

            //设值返回标志字段
            responseContext.put("api_response_msg", "匹配数据成功");
            responseContext.put("api_response_status", true);
            data.put("context", responseContext);
        } else if (resultList.size()  > 1 ){
            //当匹配到多个值时
            Map<String, Object> responseContext = new HashMap<>();
            responseContext.put("responseDataSize", resultList.size());
            responseContext.put("passwordMatchFlag", "Y");
            responseContext.put("userName", resultList.get(0).getUserName());

            //设值返回标志字段
            responseContext.put("api_response_msg", "匹配数据成功");
            responseContext.put("api_response_status", true);
            data.put("context", responseContext);
        }else if (resultList.size() == 0 ){
            //当匹配不到值时
            Map<String, Object> responseContext = new HashMap<>();
            responseContext.put("responseDataSize", resultList.size());
            responseContext.put("passwordMatchFlag", "N");

            //设值返回标志字段
            responseContext.put("api_response_status", true);
            responseContext.put("api_response_msg", "无法匹配到记录");
            data.put("context", responseContext);
        }
        return data;
    }

    /**
     * 3、账单分期-是否欠款
     *不欠款，返回0
     * @param requestContext
     * @return
     */
    private Map<String, Object> getOverdraftResult(Map<String, Object> requestContext){
        Map<String, Object> data = new HashMap<>();
        //获取全部Excel中的记录
        List<StagingInfoDto> allInfoList = stagingInfoExcelDao.getAllInfoList();

        //判断传入的内容是否匹配查询的结果值
        FilterSetterUtil<StagingInfoDto> filterSetterUtil = new FilterSetterUtil<>();
        List<StagingInfoDto> resultList = filterSetterUtil.getMatchList(requestContext, allInfoList);

        //当匹配到值时,
        if (resultList.size()  > 0 ){
            //设置返回值
            //Map<String, Object> responseContext =  filterSetterUtil.setContextValue(resultList.get(0));
            Map<String, Object> responseContext = new HashMap<>();
            responseContext.put("overdraft",resultList.get(0).getOverdraft());

            //设值返回标志字段
            responseContext.put("api_response_msg", "匹配数据成功");
            responseContext.put("api_response_status", true);
            data.put("context", responseContext);
        }else if (resultList.size() == 0 ){
            //当匹配不到值时
            Map<String, Object> responseContext = new HashMap<>();
            responseContext.put("overdraft", 0);

            //设值返回标志字段
            responseContext.put("api_response_status", true);
            responseContext.put("api_response_msg", "无法匹配到记录");
            data.put("context", responseContext);
        }
        return data;
    }

    /**
     * 4、账单分期-手续费
     *参数名	字段类型	字段描述
     * instalment	string	分期金额
     * PeriodsNO	string	分期期数
     * @param requestContext
     * @return
     */
    private Map<String, Object> getCreditRateResult(Map<String, Object> requestContext){
        Map<String, Object> data = new HashMap<>();

        String periodsNO = (String) requestContext.get("PeriodsNO");

        //获取全部Excel中的记录
        List<StagingInfoDto> allInfoList = stagingInfoExcelDao.getAllInfoList();

        //判断传入的内容是否匹配查询的结果值
        FilterSetterUtil<StagingInfoDto> filterSetterUtil = new FilterSetterUtil<>();
        //设置本次节点所需要的键（入参变量）
        List<String> goalKeys = Arrays.asList("instalment");
        List<StagingInfoDto> resultList = filterSetterUtil.getMatchList(requestContext, allInfoList, goalKeys);

        //当匹配到值时,
        if (resultList.size()  > 0 ){
            //设置返回值
            StagingInfoDto stagingInfoDto = this.getStagesInfoByNumStages(resultList.get(0),periodsNO);
            Map<String, Object> responseContext =
                    filterSetterUtil.setContextValue(stagingInfoDto);
            //设置查询到记录

            responseContext.put("recordFlag", "Y");


            //设值返回标志字段
            responseContext.put("api_response_msg", "匹配数据成功");
            data.put("context", responseContext);
        }else if (resultList.size() == 0 ){
            //当匹配不到值时
            Map<String, Object> responseContext = new HashMap<>();
            //设置查询到记录
            responseContext.put("recordFlag", "N");

            //设值返回标志字段
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
    private StagingInfoDto getStagesInfoByNumStages(StagingInfoDto dto, String numberStages){
        StagingInfoDto result = new StagingInfoDto();
        //获取两个字段集
        Field[] fields = dto.getClass().getDeclaredFields();
        Field[] fieldsSetvalue = result.getClass().getDeclaredFields();

        //根据期数进行对  手续费率%	总手续费	首期总额	各期总额  进行赋值
        for (Field perField:fields) {
            perField.setAccessible(true);
            //判断字段名最后两位是否包含输入的期数
            if(perField.getName().indexOf(numberStages, perField.getName().length() - 2) > 0){
                for (Field setValueField:fieldsSetvalue) {
                    setValueField.setAccessible(true);
                    try {
                        //对四个字段进行赋值
                        if(perField.getName().substring(0,perField.getName().length() -1).matches(setValueField.getName())){
                            setValueField.set(result,perField.get(dto));
                        }
                    }catch (IllegalAccessException e){
                        log.error("无法访问字段");
                    }
                }
            };
        }
        result.setFirstRepaymentDate(dto.getFirstRepaymentDate());
        return result;
    }
}
