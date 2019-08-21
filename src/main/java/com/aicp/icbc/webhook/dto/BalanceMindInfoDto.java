package com.aicp.icbc.webhook.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @Description: 收不到余额提醒流程-----信息dto
 * @Author: 吴开云
 * @Date: 2019/8/21 0021
 * @Version： 1.0
 */
@Data
public class BalanceMindInfoDto extends BaseRowModel {
    @ExcelProperty(value = {"姓名"}, index = 0)
    private String userName;

    @ExcelProperty(value = {"性别"}, index = 1)
    private String sex;

    @ExcelProperty(value = {"卡号"}, index = 2)
    private String cardNumber;

    @ExcelProperty(value = {"联系电话"}, index = 3)
    private String phoneNumber;

    @ExcelProperty(value = {"卡片状态"}, index = 4)
    private String cardStatus;

    @ExcelProperty(value = {"日期"}, index = 5)
    private String sysDate;

    @ExcelProperty(value = {"接收方式"}, index = 6)
    private String sysJs;

    @ExcelProperty(value = {"信息状态"}, index = 7)
    private String sysXx;

    @ExcelProperty(value = {"身份证号"}, index = 8)
    private String idCardNumber;

    @ExcelProperty(value = {"起点金额"}, index = 9)
    private String startingAmount;

}
