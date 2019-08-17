package com.aicp.icbc.webhook.utils;

import com.aicp.icbc.webhook.dto.UserInfoDto;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 对值集过滤
 * @Author: 吴开云
 * @Date: 2019/8/17 0017
 * @Version： 1.0
 */
@Slf4j
public class ValueFilteUtil <T> {
    /**
     * 从list从，过滤并返回所有属性值与context中匹配的key-value相等的行
     * @param context
     * @param list
     * @return
     */
    public List<T> getMatchList(Map<String, Object> context, List<T> list){
        List<T> resultList = new ArrayList<>();

        //对map中的key值进行遍历
        Set<String> keySet = context.keySet();

        for (T perObject:list) {
            Boolean matchFlag = true;
            Boolean containFlag = false;
            for (String key:keySet) {
                //通过反射判断每一列值中是否和传递的值相同
                Field[] fields = perObject.getClass().getDeclaredFields();
                for (Field perField:fields) {
                    perField.setAccessible(true);
                    try {
                        //判断字段名与map中的key是否匹配
                        if(key.equals(perField.getName())){
                            containFlag = true;
                            if (! context.get(key).equals(perField.get(perObject))){
                                //同相字段值不匹配
                                matchFlag = false;
                                break;
                            }
                            //字段匹配成功，判断下一个字段
                            break;
                        }
                    }catch (IllegalAccessException e){
                        log.error(key + " 属性不可访问");
                    }
                }
                if (!matchFlag && containFlag){
                    //同相字段值不匹配,匹配下一行记录
                    break;
                }
            }
            if(matchFlag && containFlag){
                resultList.add(perObject);
            }
        }
        return resultList;
    }
}
