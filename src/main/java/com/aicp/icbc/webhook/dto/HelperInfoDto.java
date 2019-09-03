package com.aicp.icbc.webhook.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @Description: 智能助手流程---信息dto
 * @Author: 吴开云
 * @Date: 2019/8/20 0020
 * @Version： 1.0
 */
@Data
public class HelperInfoDto extends BaseRowModel {
    @ExcelProperty(value = {"客户姓名"}, index = 0)
    private String userName;

    @ExcelProperty(value = {"来电手机号（柜面预留手机号）"}, index = 1)
    private String phoneNumber;

    @ExcelProperty(value = {"其他手机号（非柜面预留手机号）"}, index = 2)
    private String phoneNumberOth;

    @ExcelProperty(value = {"身份证号"}, index = 3)
    private String idCardNumber;

    @ExcelProperty(value = {"卡号"}, index = 4)
    private String cardNumber;

    @ExcelProperty(value = {"取款密码"}, index = 5)
    private String passWord;

    @ExcelProperty(value = {"账户余额"}, index = 6)
    private String accountBalance;

    @ExcelProperty(value = {"开户行"}, index = 7)
    private String bank;


}
