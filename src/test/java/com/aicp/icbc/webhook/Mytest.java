package com.aicp.icbc.webhook;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: 吴开云
 * @Date: 2019/9/15 0015
 * @Version： 1.0
 */
public class Mytest {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","3");
        map.put("2","3");
        map.put("4","4");
        String str = "12345678";
        String shustr = str.substring(0,str.length() -2);
        System.out.println(shustr);
    }
}
