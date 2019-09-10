package com.aicp.icbc.webhook;


import okhttp3.*;
import org.springframework.util.StringUtils;

import java.io.*;

/**
 * @author: liuxincheng01
 * @description: 聚类灌入会话中控接口-待标注-机器人训练
 * @date：Created in 2019-08-22 18:09
 * @modified By liuxincheng01
 */
public class Test {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\Mypc\\Desktop\\AicpInOut\\test_wav.txt")),
                    "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                if (StringUtils.startsWithIgnoreCase(lineTxt, "AGENT")) {
                    System.out.println("agent:" + lineTxt.substring(6));
                    post(lineTxt.substring(6));
                } else {
                    System.out.println("user:" + lineTxt.substring(5));
                    post(lineTxt.substring(5));
                }
            }
            br.close();
        } catch (Exception e) {
            System.err.println("read errors :" + e);
        }

    }

    public static String post(String queryText) {
        String url = "https://api-aicp.baidu.com/api/v1/core/query?version=20170407";
        OkHttpClient client = new OkHttpClient();

        HttpUrl httpUrl = HttpUrl.parse(url).newBuilder()
                .addQueryParameter("version", "20170407")
                .build();
        String json = "{\"query_text\":\"" + queryText + "\",\"session_id\":\"1234567890\"}";

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        Request request = new Request
                .Builder()
                .post(body)
                .url(httpUrl)
                .addHeader("Authorization", "AICP 0f979508-b4c7-4e79-96a9-ce13656ef997")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = "";
        try {
            str = response.body().string();
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
