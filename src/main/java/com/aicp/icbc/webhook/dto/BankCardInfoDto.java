package com.aicp.icbc.webhook.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @Description: 银行卡挂失流程-----银行卡信息dto
 * @Author: 吴开云
 * @Date: 2019/8/20 0020
 * @Version： 1.0
 */
@Data
public class BankCardInfoDto extends BaseRowModel {
    @ExcelProperty(value = {"姓名"}, index = 0)
    private String userName;

    @ExcelProperty(value = {"性别"}, index = 1)
    private String sex;

    @ExcelProperty(value = {"证件类型"}, index = 2)
    private String certificateType;

    @ExcelProperty(value = {"证件号码"}, index = 3)
    private String certificateId;

    @ExcelProperty(value = {"出生日期"}, index = 4)
    private String dateBirth;

    @ExcelProperty(value = {"卡号"}, index = 5)
    private String cardNumber;

    @ExcelProperty(value = {"卡/账户类型"}, index = 6)
    private String accountType;

    @ExcelProperty(value = {"开户日期"}, index = 7)
    private String accountOpeningDate;

    @ExcelProperty(value = {"密码"}, index = 8)
    private String password;

    @ExcelProperty(value = {"联系电话"}, index = 9)
    private String phoneNumber;

    @ExcelProperty(value = {"开户行"}, index = 10)
    private String bank;

    @ExcelProperty(value = {"家庭地址"}, index = 11)
    private String familyAddress;

    @ExcelProperty(value = {"账户余额"}, index = 12)
    private String accountBalance;

    @ExcelProperty(value = {"账户状态"}, index = 13)
    private String accountStatus;

    /**
     * 卡号后四位
     */
    private String cardNumberFour;


}
