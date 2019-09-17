package com.aicp.icbc.webhook.service.impl;

import com.aicp.icbc.webhook.dao.BankCardInfoExcelDao;
import com.aicp.icbc.webhook.dto.BankCardInfoDto;
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
 * @DESC:储蓄卡挂失流程server
 */
@Service("BankCardInfoService")
@Slf4j
public class BankCardInfoServiceImpl implements BusinessService {
    @Autowired
    private BankCardInfoExcelDao infoExcelDao;

    public final String ACTION_GET_CUSTOMER_AUTHENTICATION = "getcustomerAuthentication";

    public final String ACTION_GET_ID_NUMBER = "getidNumber";

    public final String ACTION_GET_CARD_NOT_FOUR = "getcardNoFour";

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
        //1、储蓄卡挂失-客户身份验证
        if(this.ACTION_GET_CUSTOMER_AUTHENTICATION.equals(action)){
            return this.getcustomerAuthenticationResult(requestContext);
        }

        //2、储蓄卡挂失-客户身份验证
        if(this.ACTION_GET_ID_NUMBER.equals(action)){
            return this.getidNumberResult(requestContext);
        }

        //3、储蓄卡挂失-客户身份验证
        if(this.ACTION_GET_CARD_NOT_FOUR.equals(action)){
            return this.getcardNoFour(requestContext);
        }


        return new HashMap<>();
    }

    /**
     *1、储蓄卡挂失-客户身份验证
     *入参
     * 参数名	字段类型	字段描述	备注
     * CardNO	string	银行卡卡号	返回卡号
     * 出参
     * 参数名	字段类型	字段描述	备注
     * matchFlag	string	是否成功匹配数据	返回值为Y/N，分别表示成功与失败
     * 			返回全部字段信息
     * @param requestContext
     * @return
     */
    private Map<String, Object> getcustomerAuthenticationResult(Map<String, Object> requestContext){
        Map<String, Object> data = new HashMap<>();

        //银行卡号
        String CardNO = (String)requestContext.get("CardNO");
        //替换原本的卡号字段
        if(!StringUtils.isEmpty(CardNO)){
            requestContext.put("cardNo",CardNO);
        }

        //证件号码
        String IDNumber = (String)requestContext.get("IDNumber");
        //替换原本的证件号码字段
        if(!StringUtils.isEmpty(IDNumber)){
            requestContext.put("idNumber",IDNumber);
        }


        //获取全部Excel中的记录
        List<BankCardInfoDto> allInfoList = infoExcelDao.getAllInfoList();

        //判断传入的内容是否匹配查询的结果值
        FilterSetterUtil<BankCardInfoDto> filterSetterUtil = new FilterSetterUtil<>();
        //设置本次节点所需要的键（入参变量）
        List<String> goalKeys = Arrays.asList("cardNo","idNumber");
        List<BankCardInfoDto> resultList = filterSetterUtil.getMatchList(requestContext, allInfoList, goalKeys);

        if (resultList.size() > 0) {
            //将返回的对象进行key-value赋值
            Map<String, Object> responseContext = filterSetterUtil.setContextValue(resultList.get(0));
            //设值返回标志字段
            responseContext.put("matchFlag", "Y");
            //判断是否有卡号--默认为N
            responseContext.put("cardFlag", "N");
            for (Integer i = 0; i < resultList.size() ; i ++) {
                BankCardInfoDto perDto = resultList.get(i);
                //更新是否有卡号标识
                if(!StringUtils.isEmpty(resultList.get(i).getCardNo())){
                    responseContext.put("cardFlag", "Y");
                }
            }

            responseContext.put("api_response_msg", "匹配数据成功");
            responseContext.put("api_response_status", true);
            data.put("context", responseContext);
        } else if (resultList.size()  == 0 ){
            //当未匹配到值时
            Map<String, Object> responseContext = new HashMap<>();
            responseContext.put("matchFlag", "N");
            //判断是否有卡号--默认为N
            responseContext.put("cardFlag", "N");
            responseContext.put("api_response_msg", "无法匹配到记录");
            responseContext.put("api_response_status", true);
            data.put("context", responseContext);
        }
        return data;
    }


    /**
     * 2、储蓄卡挂失-客户身份验证
     *参数名	字段类型	字段描述	备注
     * idNumber	string	身份证号
     * 出参
     * 参数名	字段类型	字段描述	备注
     * cardNoFour	string	卡号后四位	仅返回所有的卡号后四位，返回字符串类型
     * recordFlag	string		判断卡号是否唯一，仅有一张卡输出Y；多张卡为N
     * @param requestContext
     * @return
     */
    private Map<String, Object> getidNumberResult(Map<String, Object> requestContext){
        Map<String, Object> data = new HashMap<>();
        //获取全部Excel中的记录
        List<BankCardInfoDto> allInfoList = infoExcelDao.getAllInfoList();

        //判断传入的内容是否匹配查询的结果值
        FilterSetterUtil<BankCardInfoDto> filterSetterUtil = new FilterSetterUtil<>();
        List<String> goalKeys = Arrays.asList("idNumber");
        List<BankCardInfoDto> resultList = filterSetterUtil.getMatchList(requestContext, allInfoList, goalKeys);

        if (resultList.size() == 1) {
            //当匹配到唯一值时,
            //将返回的对象进行key-value赋值
            Map<String, Object> responseContext = filterSetterUtil.setContextValue(resultList.get(0));
            //设置返回字段值
            //判断是否命中身份证
            responseContext.put("matchFlag", "Y");
            //判断是否只有一张卡
            responseContext.put("recordFlag", "Y");
            //判断是否有卡号
            if(!StringUtils.isEmpty(resultList.get(0).getCardNo())){
                //更新卡号数量
                responseContext.put("carNoBackFourStr",resultList.get(0).getCarNoBackFour());
                //更新是否有卡号标识
                responseContext.put("cardFlag", "Y");
            }else {
                responseContext.put("cardFlag", "N");
            }
            //设值返回标志字段
            responseContext.put("api_response_msg", "匹配数据成功");
            responseContext.put("api_response_status", true);
            data.put("context", responseContext);
        } else if (resultList.size()  > 1 ){
            //当匹配到多个值时
            Map<String, Object> responseContext = new HashMap<>();
            //判断是否命中身份证
            responseContext.put("matchFlag", "Y");
            //判断是否只有一张卡
            responseContext.put("recordFlag", "N");
            //判断是否有卡号--默认为N
            responseContext.put("cardFlag", "N");

            //取全部的卡号后四位
            String carNoBackFourStr = "";
            for (Integer i = 0, j = 0; i < resultList.size() ; i ++) {
                BankCardInfoDto perDto = resultList.get(i);
                //更新是否有卡号标识
                if(!StringUtils.isEmpty(resultList.get(i).getCardNo())){
                    responseContext.put("cardFlag", "Y");
                    //更新卡号数量
                    if(j == 0){
                        carNoBackFourStr += "卡号"+(j+1) + "、"+perDto.getCarNoBackFour();
                    }else {
                        carNoBackFourStr += "；" + "卡号"+(j+1) + "、"+perDto.getCarNoBackFour();
                    }
                    j ++;
                }
            }
            responseContext.put("carNoBackFourStr",carNoBackFourStr);

            //设值返回标志字段
            responseContext.put("api_response_msg", "匹配数据成功");
            responseContext.put("api_response_status", true);
            data.put("context", responseContext);
        }else if (resultList.size() == 0 ){
            //当匹配不到值时
            Map<String, Object> responseContext = new HashMap<>();
            //判断是否命中身份证
            responseContext.put("matchFlag", "N");
            //设值返回标志字段
            responseContext.put("api_response_status", true);
            responseContext.put("api_response_msg", "无法匹配到记录");
            data.put("context", responseContext);
        }
        return data;
    }


    /**
     *3、储蓄卡挂失-客户身份验证
     入参
     参数名	字段类型	字段描述	备注
     carNoBackFour	string	卡号后四位
     出参
     参数名	字段类型	字段描述	备注
     返回全部字段信息
     matchFlag	String	是否成功匹配数据	返回值为Y/N，分别表示成功与失败
     * 			返回全部字段信息
     * @param requestContext
     * @return
     */
    private Map<String, Object> getcardNoFour(Map<String, Object> requestContext){
        Map<String, Object> data = new HashMap<>();

        //获取全部Excel中的记录
        List<BankCardInfoDto> allInfoList = infoExcelDao.getAllInfoList();

        //判断传入的内容是否匹配查询的结果值
        FilterSetterUtil<BankCardInfoDto> filterSetterUtil = new FilterSetterUtil<>();
        //设置本次节点所需要的键（入参变量）
        List<String> goalKeys = Arrays.asList("carNoBackFour","idNumber");
        List<BankCardInfoDto> resultList = filterSetterUtil.getMatchList(requestContext, allInfoList, goalKeys);

        if (resultList.size() > 0) {
            //将返回的对象进行key-value赋值
            Map<String, Object> responseContext = filterSetterUtil.setContextValue(resultList.get(0));
            //设值返回标志字段
            responseContext.put("matchFlag", "Y");
            responseContext.put("api_response_msg", "匹配数据成功");
            responseContext.put("api_response_status", true);
            data.put("context", responseContext);
        } else if (resultList.size()  == 0 ){
            //当未匹配到值时
            Map<String, Object> responseContext = new HashMap<>();
            responseContext.put("matchFlag", "N");
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
