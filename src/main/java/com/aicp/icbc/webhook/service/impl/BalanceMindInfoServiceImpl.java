package com.aicp.icbc.webhook.service.impl;

import com.aicp.icbc.webhook.dao.BalanceMindInfoExcelDao;
import com.aicp.icbc.webhook.dto.BalanceMindInfoDto;
import com.aicp.icbc.webhook.service.BusinessService;
import com.aicp.icbc.webhook.utils.CommonUtils;
import com.aicp.icbc.webhook.utils.FilterSetterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @DESC:收不到余额变动提醒流程server
 */
@Service("BalanceMindInfoService")
@Slf4j
public class BalanceMindInfoServiceImpl implements BusinessService {
    @Autowired
    private BalanceMindInfoExcelDao infoExcelDao;

    public final String ACTION_GET_BALANCE_MIND_INFO = "getBalanceMindInfo";

    public final String ACTION_GET_BALANCE_MIND_SYSDATE = "getBalanceMindSysdate";


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
        //1、客户信息获取
        if(this.ACTION_GET_BALANCE_MIND_INFO.equals(action)){
            return this.getBalanceMindInfoResult(requestContext);
        }
        //2、日期记录验证
        if(this.ACTION_GET_BALANCE_MIND_SYSDATE.equals(action)){
            return this.getBalanceMindSysdateResult(requestContext);
        }

        return new HashMap<>();
    }

    /**
     * 1、收不到余额提醒--获取列表行信息
     * @param requestContext
     * @return
     */
    private Map<String, Object> getBalanceMindInfoResult(Map<String, Object> requestContext){
        Map<String, Object> data = new HashMap<>();

        //获取全部Excel中的记录
        List<BalanceMindInfoDto> allInfoList = infoExcelDao.getAllInfoList();

        //判断传入的内容是否匹配查询的结果值
        FilterSetterUtil<BalanceMindInfoDto> filterSetterUtil = new FilterSetterUtil<>();
        List<BalanceMindInfoDto> resultList = filterSetterUtil.getMatchList(requestContext, allInfoList);

        //当匹配到值时,
        if (resultList.size() > 0) {
            //将返回的对象进行key-value赋值
            Map<String, Object> responseContext = filterSetterUtil.setContextValue(resultList.get(0));

            responseContext.put("recordFlag", "Y");
            responseContext.put("responseDataSize",resultList.size());

            //设值返回标志字段
            responseContext.put("api_response_msg", "匹配数据成功");
            responseContext.put("api_response_status", true);
            data.put("context", responseContext);
        } else if (resultList.size()  == 0 ){
            //当未匹配到值时
            Map<String, Object> responseContext = new HashMap<>();
            responseContext.put("recordFlag", "N");
            responseContext.put("responseDataSize",resultList.size());

            //设值返回标志字段
            responseContext.put("api_response_msg", "无法匹配到记录");
            responseContext.put("api_response_status", true);
            data.put("context", responseContext);
        }
        return data;
    }

    /**
     * 2、收不到余额提醒--判读日期是否存在记录
     *不存在记录，则返回N, 存在返回Y
     * @param requestContext
     * @return
     */
    private Map<String, Object> getBalanceMindSysdateResult(Map<String, Object> requestContext){
        Map<String, Object> data = new HashMap<>();

        //第二次验证交易记录日期字段
        String jyjlrq = (String)requestContext.get("jyjlrq");
        //替换原本的日志字段
        requestContext.put("sysDate",jyjlrq);

        //获取全部Excel中的记录
        List<BalanceMindInfoDto> allInfoList = infoExcelDao.getAllInfoList();

        //判断传入的内容是否匹配查询的结果值
        FilterSetterUtil<BalanceMindInfoDto> filterSetterUtil = new FilterSetterUtil<>();
        List<BalanceMindInfoDto> resultList = filterSetterUtil.getMatchList(requestContext, allInfoList);


        //当匹配到值时,
        if (resultList.size() > 0) {
            //将返回的对象进行key-value赋值
            Map<String, Object> responseContext = filterSetterUtil.setContextValue(resultList.get(0));

            responseContext.put("recordFlag", "Y");
            responseContext.put("responseDataSize",resultList.size());

            //设值返回标志字段
            responseContext.put("api_response_msg", "匹配数据成功");
            responseContext.put("api_response_status", true);
            data.put("context", responseContext);
        } else if (resultList.size()  == 0 ){
            //当未匹配到值时
            Map<String, Object> responseContext = new HashMap<>();
            responseContext.put("recordFlag", "N");
            responseContext.put("responseDataSize",resultList.size());

            //设值返回标志字段
            responseContext.put("api_response_msg", "无法匹配到记录");
            responseContext.put("api_response_status", true);
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
