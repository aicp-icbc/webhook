package com.aicp.icbc.webhook.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 请求信息功具类
 * @Author: 吴开云
 * @Date: 2019/8/16 0016
 * @Version： 1.0
 */
@Slf4j
public class RequestUtils {
    /**
     * 解析查询请求
     * @param requestBody
     * @return
     */
    public static Map<String, Object> getRequest(String requestBody)  {
        Map<String, Object> map = new HashMap<>();
        try {
            map = CommonUtils.JACKSON_OBJECT_MAPPER.readValue(requestBody, HashMap.class);
        }catch (IOException e){
            log.error(e.getMessage());
        }
        return map;
    }


    /**
     *请求行方接口
     * @return
     */
    public static String loadXmlStrFromBank(String from, String cardNo){
        //固定请求报文格式
        String xmlStr = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                "<CC>\n" +
                "    <PUB>\n" +
                "        <SPTRANSCODE>L8899</SPTRANSCODE>\n" +
                "    </PUB>\n" +
                "    <OUT>\n" +
                "        <WHERE>cardNo="+cardNo+"</WHERE>\n" +
                "        <FROM>"+from+"</FROM>\n" +
                "        <SELECT>all</SELECT>\n" +
                "        <sChannel>4</sChannel>\n" +
                "    </OUT>\n" +
                "</CC>";
        //编写地址
        String url = "http://122.19.125.70:11777/ICBCCallCenter/IVRGBK";
        //填充请求体
        RequestBody body = RequestBody.create(MediaType.parse("text/xml"),xmlStr);
        //初始化请求
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type","text/xml")
                .post(body).build();

        Response response = null;
        String resultStr = "";
        try {
            //执行请求
            response = new OkHttpClient().newCall(request).execute();
            //获取返回体
            if(response != null){
                if(response.isSuccessful()){
                    resultStr = response.body().string();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return resultStr;
    }
}
