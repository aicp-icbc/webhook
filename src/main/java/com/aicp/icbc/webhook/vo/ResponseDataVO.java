package com.aicp.icbc.webhook.vo;

import lombok.Data;

/**
 * @Description: 信息返回实体
 * @Author: 吴开云
 * @Date: 2019/8/16 0016
 * @Version： 1.0
 */
@Data
public class ResponseDataVO<T> {
    /**
     * 状态码
     */
    private int code ;

    /**
     * 时间戳
     */
    private long time ;

    /**
     * 状态信息
     */
    private String msg ;

    /**
     * 返回数据
     */
    private T data;


}
