package com.aicp.icbc.webhook.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @Description: 信用卡分期外呼流程的信息DTO --- 对应Excel表格的第4个sheet页，分期信息
 * @Author: 吴开云
 * @Date: 2019/8/19 0019
 * @Version： 1.0
 */
@Data
public class CreditCardStagesInfoDto extends BaseRowModel {

    @ExcelProperty(value = {"卡号"}, index = 0)
    private String cardNumber;

    @ExcelProperty(value = {"姓名"}, index = 1)
    private String userName;

    @ExcelProperty(value = {"性别"}, index = 2)
    private String sex;

    @ExcelProperty(value = {"可分期金额"}, index = 3)
    private String instalment;

    @ExcelProperty(value = {"首期本金-3期"}, index = 4)
    private String initialPrincipal3;

    @ExcelProperty(value = {"月均需还本金-3期"}, index = 5)
    private String monthlyPrincipal3;

    @ExcelProperty(value = {"首期需还手续费-3期"}, index = 6)
    private String initialCharges3;

    @ExcelProperty(value = {"月均需还手续费-3期"}, index = 7)
    private String monthlyCharges3;

    @ExcelProperty(value = {"费率-3期"}, index = 8)
    private String rate3;

    @ExcelProperty(value = {"总手续费-3期"}, index = 9)
    private String totalrate3;

    @ExcelProperty(value = {"首期本金-6期"}, index = 10)
    private String initialPrincipal6;

    @ExcelProperty(value = {"月均需还本金-6期"}, index = 11)
    private String monthlyPrincipal6;

    @ExcelProperty(value = {"首期需还手续费-6期"}, index = 12)
    private String initialCharges6;

    @ExcelProperty(value = {"月均需还手续费-6期"}, index = 13)
    private String monthlyCharges6;

    @ExcelProperty(value = {"费率-6期"}, index = 14)
    private String rate6;

    @ExcelProperty(value = {"总手续费-6期"}, index = 15)
    private String totalrate6;

    @ExcelProperty(value = {"首期本金-9期"}, index = 16)
    private String initialPrincipal9;

    @ExcelProperty(value = {"月均需还本金-9期"}, index = 17)
    private String monthlyPrincipal9;

    @ExcelProperty(value = {"首期需还手续费-9期"}, index = 18)
    private String initialCharges9;

    @ExcelProperty(value = {"月均需还手续费-9期"}, index = 19)
    private String monthlyCharges9;

    @ExcelProperty(value = {"费率-9期"}, index = 20)
    private String rate9;

    @ExcelProperty(value = {"总手续费-9期"}, index = 21)
    private String totalrate9;

    @ExcelProperty(value = {"首期本金-12期"}, index = 22)
    private String initialPrincipal12;

    @ExcelProperty(value = {"月均需还本金-12期"}, index = 23)
    private String monthlyPrincipal12;

    @ExcelProperty(value = {"首期需还手续费-12期"}, index = 24)
    private String initialCharges12;

    @ExcelProperty(value = {"月均需还手续费-12期"}, index = 25)
    private String monthlyCharges12;

    @ExcelProperty(value = {"费率-12期"}, index = 26)
    private String rate12;

    @ExcelProperty(value = {"总手续费-12期"}, index = 27)
    private String totalrate12;

    @ExcelProperty(value = {"首期本金-18期"}, index = 28)
    private String initialPrincipal18;

    @ExcelProperty(value = {"月均需还本金-18期"}, index = 29)
    private String monthlyPrincipal18;

    @ExcelProperty(value = {"首期需还手续费-18期"}, index = 30)
    private String initialCharges18;

    @ExcelProperty(value = {"月均需还手续费-18期"}, index = 31)
    private String monthlyCharges18;

    @ExcelProperty(value = {"费率-18期"}, index = 32)
    private String rate18;

    @ExcelProperty(value = {"总手续费-18期"}, index = 33)
    private String totalrate18;

    @ExcelProperty(value = {"首期本金-24期"}, index = 34)
    private String initialPrincipal24;

    @ExcelProperty(value = {"月均需还本金-24期"}, index = 35)
    private String monthlyPrincipal24;

    @ExcelProperty(value = {"首期需还手续费-24期"}, index = 36)
    private String initialCharges24;

    @ExcelProperty(value = {"月均需还手续费-24期"}, index = 37)
    private String monthlyCharges24;

    @ExcelProperty(value = {"费率-24期"}, index = 38)
    private String rate24;

    @ExcelProperty(value = {"总手续费-24期"}, index = 39)
    private String totalrate24;

    @ExcelProperty(value = {"主叫号码"}, index = 40)
    private String phoneNumber;

    @ExcelProperty(value = {"出生日期"}, index = 41)
    private String dateBirth;

    /**
     * 首期本金
     */
    private String initialPrincipal;

    /**
     * 月均需还本金
     */
    private String monthlyPrincipal;

    /**
     * 首期需还手续费
     */
    private String initialCharges;

    /**
     * 月均需还手续费
     */
    private String monthlyCharges;

    /**
     * 费率
     */
    private String rate;

    /**
     * 总手续费
     */
    private String totalrate;

    /**
     * 银行卡后四位
     */
    private String cardNumberFour;

}
