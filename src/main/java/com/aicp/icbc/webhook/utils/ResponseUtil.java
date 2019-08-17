package com.aicp.icbc.webhook.utils;

import com.aicp.icbc.webhook.VO.ResponseDataVO;
import com.aicp.icbc.webhook.enums.ResponseStatusEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Description: 返回信息工具类
 * @Author: 吴开云
 * @Date: 2019/8/16 0016
 * @Version： 1.0
 */
@Slf4j
public class ResponseUtil {

    /**
     * 请求成功
     * @param object
     * @return
     */
    public static String usccess(Object object){
        ResponseDataVO responseVO = new ResponseDataVO();
        responseVO.setCode(ResponseStatusEnum.Success.getStatus());
        responseVO.setMsg(ResponseStatusEnum.Success.getMessage());
        responseVO.setTime(System.currentTimeMillis());
        responseVO.setData(object);
        String resultStr = "";
        try {
            resultStr = CommonUtils.JACKSON_OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(responseVO);
        }catch (IOException e){
            log.error(e.getMessage());
            resultStr = responseVO.toString();
        }
        return resultStr;
    }

    /**
     * 请求成功
     * @param object
     * @return
     */
    public static String serverNotMatch(Object object){
        ResponseDataVO responseVO = new ResponseDataVO();
        responseVO.setCode(ResponseStatusEnum.ServerNotMatch.getStatus());
        responseVO.setMsg(ResponseStatusEnum.ServerNotMatch.getMessage());
        responseVO.setTime(System.currentTimeMillis());
        responseVO.setData(object);
        String resultStr = "";
        try {
            resultStr = CommonUtils.JACKSON_OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(responseVO);
        }catch (IOException e){
            log.error(e.getMessage());
            resultStr = responseVO.toString();
        }
        return resultStr;
    }

}
