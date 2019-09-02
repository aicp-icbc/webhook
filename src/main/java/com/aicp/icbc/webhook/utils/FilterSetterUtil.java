package com.aicp.icbc.webhook.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Description: 对值集过滤
 * @Author: 吴开云
 * @Date: 2019/8/17 0017
 * @Version： 1.0
 */
@Slf4j
public class FilterSetterUtil<T> {
    /**
     * 从list从，过滤并返回所有属性值与context中匹配的key-value相等的行，只取context中goalKeys的值
     * @param context
     * @param list
     * @return
     */
    public List<T> getMatchList(Map<String, Object> context, List<T> list, List<String> goalKeys){
        List<T> resultList = new ArrayList<>();

        //对map中的key值进行遍历
        Set<String> keySet = context.keySet();

        for (T perObject:list) {
            Boolean matchFlag = true;
            Boolean containFlag = false;
            for (String key:keySet) {
                //判断KEY是否为所本次节点中需要的KEY
                if(goalKeys.contains(key)){
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

    /**
     * 将传递的对象中，所有的属性，赋值在map中，以(字段名，字段值)的方式。
     * @param object
     * @return
     */
    public Map<String, Object> setContextValue(T object){
        Map<String, Object> context = new HashMap<>();
        //取所有定义字段
        Field[] fields = object.getClass().getDeclaredFields();

        //判断字段值是否为空
        for (Field perField:fields) {
            perField.setAccessible(true);
            try {
                if(!StringUtils.isEmpty(perField.get(object))){
                    //取非空字段进行 key，value赋值
                    context.put(perField.getName(),perField.get(object));
                }
            }catch (IllegalAccessException e){
                e.printStackTrace();
                log.error("无法访问字段");
            }
        }
        return context;
    }
}
