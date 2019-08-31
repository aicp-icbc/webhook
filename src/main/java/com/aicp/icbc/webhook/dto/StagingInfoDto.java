package com.aicp.icbc.webhook.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @Description: 账单分期流程的信息DTO
 * @Author: 吴开云
 * @Date: 2019/8/19 0019
 * @Version： 1.0
 */
@Data
public class StagingInfoDto extends BaseRowModel {
    @ExcelProperty(value = {"姓名"}, index = 0)
    private String userName;

    @ExcelProperty(value = {"身份证号"}, index = 1)
    private String idCardNumber;

    @ExcelProperty(value = {"卡号"}, index = 2)
    private String cardNumber;

    @ExcelProperty(value = {"密码"}, index = 3)
    private String password;

    @ExcelProperty(value = {"客户进入身份"}, index = 4)
    private String registerFlag;

    @ExcelProperty(value = {"主副卡身份"}, index = 5)
    private String mainOrElse;

    @ExcelProperty(value = {"账单分期 办理卡种"}, index = 6)
    private String cardType;

    @ExcelProperty(value = {"账户余额"}, index = 7)
    private String Overdraft;

    @ExcelProperty(value = {"欠款币种"}, index = 8)
    private String Currency;

    @ExcelProperty(value = {"可分期金额"}, index = 9)
    private String instalment;

    @ExcelProperty(value = {"首次扣款时间"}, index = 10)
    private String FirstRepaymentDate;

    @ExcelProperty(value = {"当前账户额度"}, index = 11)
    private String MaxInstalment;

    @ExcelProperty(value = {"分期后剩余额度"}, index = 12)
    private String remainingAmount;

    @ExcelProperty(value = {"首期本金-3期"}, index = 13)
    private String ServiceChargeFate3;

    @ExcelProperty(value = {"月均需还本金-3期"}, index = 14)
    private String ServiceCharge3;

    @ExcelProperty(value = {"首期需还手续费-3期"}, index = 15)
    private String FirstRepaymentMoney3;

    @ExcelProperty(value = {"月均需还手续费-3期"}, index = 16)
    private String RepaymentMoney3;

    @ExcelProperty(value = {"首期本金-6期"}, index = 17)
    private String ServiceChargeFate6;

    @ExcelProperty(value = {"月均需还本金-6期"}, index = 18)
    private String ServiceCharge6;

    @ExcelProperty(value = {"首期需还手续费-6期"}, index = 19)
    private String FirstRepaymentMoney6;

    @ExcelProperty(value = {"月均需还手续费-6期"}, index = 20)
    private String RepaymentMoney6;

    @ExcelProperty(value = {"首期本金-9期"}, index = 21)
    private String ServiceChargeFate9;

    @ExcelProperty(value = {"月均需还本金-9期"}, index = 22)
    private String ServiceCharge9;

    @ExcelProperty(value = {"首期需还手续费-9期"}, index = 23)
    private String FirstRepaymentMoney9;

    @ExcelProperty(value = {"月均需还手续费-9期"}, index = 24)
    private String RepaymentMoney9;

    @ExcelProperty(value = {"首期本金-12期"}, index = 25)
    private String ServiceChargeFate12;

    @ExcelProperty(value = {"月均需还本金-12期"}, index = 26)
    private String ServiceCharge12;

    @ExcelProperty(value = {"首期需还手续费-12期"}, index = 27)
    private String FirstRepaymentMoney12;

    @ExcelProperty(value = {"月均需还手续费-12期"}, index = 28)
    private String RepaymentMoney12;

    @ExcelProperty(value = {"首期本金-18期"}, index = 29)
    private String ServiceChargeFate18;

    @ExcelProperty(value = {"月均需还本金-18期"}, index = 30)
    private String ServiceCharge18;

    @ExcelProperty(value = {"首期需还手续费-18期"}, index = 31)
    private String FirstRepaymentMoney18;

    @ExcelProperty(value = {"月均需还手续费-18期"}, index = 32)
    private String RepaymentMoney18;

    @ExcelProperty(value = {"首期本金-24期"}, index = 33)
    private String ServiceChargeFate24;

    @ExcelProperty(value = {"月均需还本金-24期"}, index = 34)
    private String ServiceCharge24;

    @ExcelProperty(value = {"首期需还手续费-24期"}, index = 35)
    private String FirstRepaymentMoney24;

    @ExcelProperty(value = {"月均需还手续费-24期"}, index = 36)
    private String RepaymentMoney24;


    /**
     * 首期本金
     */
    private String ServiceChargeFate;

    /**
     * 月均需还本金
     */
    private String ServiceCharge;

    /**
     * 首期需还手续费
     */
    private String FirstRepaymentMoney;

    /**
     * 月均需还手续费
     */
    private String RepaymentMoney;


}
