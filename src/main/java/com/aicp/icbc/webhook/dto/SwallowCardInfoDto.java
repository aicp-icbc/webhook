package com.aicp.icbc.webhook.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @Description:吞卡信息流程的信息DTO
 * @Author: 吴开云
 * @Date: 2019/8/19 0019
 * @Version： 1.0
 */
@Data
public class SwallowCardInfoDto extends BaseRowModel {

    @ExcelProperty(value = {"姓名"}, index = 0)
    private String userName;

    @ExcelProperty(value = {"证件号"}, index = 1)
    private String idCardNumber;

    @ExcelProperty(value = {"卡号"}, index = 2)
    private String cardNumber;

    @ExcelProperty(value = {"密码"}, index = 3)
    private String password;

    @ExcelProperty(value = {"联系电话"}, index = 4)
    private String phoneNumber;

    @ExcelProperty(value = {"机具编号"}, index = 5)
    private String machineNum;

    @ExcelProperty(value = {"机具位置"}, index = 6)
    private String eatLocation;

    @ExcelProperty(value = {"管理网点"}, index = 7)
    private String bank;

    @ExcelProperty(value = {"吞卡时间"}, index = 8)
    private String eatTime;

    @ExcelProperty(value = {"系统记录情况"}, index = 9)
    private String systemRecordFlag;

    /**
     * 银行卡后四位
     */
    private String cardNumberFour;

    /**
     * 银行卡前六位
     */
    private String cardNumberPreSix;
}
