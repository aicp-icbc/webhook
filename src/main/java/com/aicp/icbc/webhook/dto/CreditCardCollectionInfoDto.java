package com.aicp.icbc.webhook.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @Desc: 信用卡催收外呼流程中的身份信息DTO
 */
@Data
public class CreditCardCollectionInfoDto extends BaseRowModel {
    @ExcelProperty(value = {"姓名"}, index = 0)
    private String userName;

    @ExcelProperty(value = {"性别"}, index = 1)
    private String sex;

    @ExcelProperty(value = {"卡号"}, index = 2)
    private String cardNumber;

    @ExcelProperty(value = {"币种"}, index = 3)
    private String currency;

    @ExcelProperty(value = {"账单日"}, index = 4)
    private String statementDate;

    @ExcelProperty(value = {"期限段"}, index = 5)
    private String durationPeriod;

    @ExcelProperty(value = {"本息合计"}, index = 6)
    private String totalPrincipalInterest;

    @ExcelProperty(value = {"最低还款额"}, index = 7)
    private String minimumPayment;

    @ExcelProperty(value = {"最优还款额"}, index = 8)
    private String optimalPayment;

    @ExcelProperty(value = {"联系电话"}, index = 9)
    private String phoneNumber;

    @ExcelProperty(value = {"发卡地区"}, index = 10)
    private String cardingArea;

    @ExcelProperty(value = {"卡片状态"}, index = 11)
    private String cardStatus;

    @ExcelProperty(value = {"还款日"}, index = 12)
    private String repaymentDate;

}
