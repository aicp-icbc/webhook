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
    private String overdraft;

    @ExcelProperty(value = {"欠款币种"}, index = 8)
    private String currency;

    @ExcelProperty(value = {"可分期金额"}, index = 9)
    private String instalment;

    @ExcelProperty(value = {"首次扣款时间"}, index = 10)
    private String firstRepaymentDate;

    @ExcelProperty(value = {"当前账户额度"}, index = 11)
    private String maxInstalment;

    @ExcelProperty(value = {"分期后剩余额度"}, index = 12)
    private String remainingAmount;

    @ExcelProperty(value = {"首期本金-3期"}, index = 13)
    private String serviceChargeFate3;

    @ExcelProperty(value = {"月均需还本金-3期"}, index = 14)
    private String serviceCharge3;

    @ExcelProperty(value = {"首期需还手续费-3期"}, index = 15)
    private String firstRepaymentMoney3;

    @ExcelProperty(value = {"月均需还手续费-3期"}, index = 16)
    private String repaymentMoney3;

    @ExcelProperty(value = {"首期本金-6期"}, index = 17)
    private String serviceChargeFate6;

    @ExcelProperty(value = {"月均需还本金-6期"}, index = 18)
    private String serviceCharge6;

    @ExcelProperty(value = {"首期需还手续费-6期"}, index = 19)
    private String firstRepaymentMoney6;

    @ExcelProperty(value = {"月均需还手续费-6期"}, index = 20)
    private String repaymentMoney6;

    @ExcelProperty(value = {"首期本金-9期"}, index = 21)
    private String serviceChargeFate9;

    @ExcelProperty(value = {"月均需还本金-9期"}, index = 22)
    private String serviceCharge9;

    @ExcelProperty(value = {"首期需还手续费-9期"}, index = 23)
    private String firstRepaymentMoney9;

    @ExcelProperty(value = {"月均需还手续费-9期"}, index = 24)
    private String repaymentMoney9;

    @ExcelProperty(value = {"首期本金-12期"}, index = 25)
    private String serviceChargeFate12;

    @ExcelProperty(value = {"月均需还本金-12期"}, index = 26)
    private String serviceCharge12;

    @ExcelProperty(value = {"首期需还手续费-12期"}, index = 27)
    private String firstRepaymentMoney12;

    @ExcelProperty(value = {"月均需还手续费-12期"}, index = 28)
    private String repaymentMoney12;

    @ExcelProperty(value = {"首期本金-18期"}, index = 29)
    private String serviceChargeFate18;

    @ExcelProperty(value = {"月均需还本金-18期"}, index = 30)
    private String serviceCharge18;

    @ExcelProperty(value = {"首期需还手续费-18期"}, index = 31)
    private String firstRepaymentMoney18;

    @ExcelProperty(value = {"月均需还手续费-18期"}, index = 32)
    private String repaymentMoney18;

    @ExcelProperty(value = {"首期本金-24期"}, index = 33)
    private String serviceChargeFate24;

    @ExcelProperty(value = {"月均需还本金-24期"}, index = 34)
    private String serviceCharge24;

    @ExcelProperty(value = {"首期需还手续费-24期"}, index = 35)
    private String firstRepaymentMoney24;

    @ExcelProperty(value = {"月均需还手续费-24期"}, index = 36)
    private String repaymentMoney24;

    /**
     * 首期本金
     */
    private String serviceChargeFate;

    /**
     * 月均需还本金
     */
    private String serviceCharge;

    /**
     * 首期需还手续费
     */
    private String firstRepaymentMoney;

    /**
     * 月均需还手续费
     */
    private String repaymentMoney;

}
