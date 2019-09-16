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

    @ExcelProperty(value = {"卡号"}, index = 1)
    private String cardNumber;

    @ExcelProperty(value = {"卡类型"}, index = 2)
    private String cardType;

    @ExcelProperty(value = {"注册标识"}, index = 3)
    private String zcbs;

    @ExcelProperty(value = {"密码"}, index = 4)
    private String password;

    @ExcelProperty(value = {"余额变动提醒是否制定"}, index = 5)
    private String zhiding;

    @ExcelProperty(value = {"有明细的交易日期"}, index = 6)
    private String sysDate;

    @ExcelProperty(value = {"当天交易明细笔数金额"}, index = 7)
    private String detail;

    @ExcelProperty(value = {"接收方式"}, index = 8)
    private String sysJs;

    @ExcelProperty(value = {"发送状态"}, index = 9)
    private String sysXx;

    @ExcelProperty(value = {"起点提醒金额"}, index = 10)
    private String startedMoney;

    @ExcelProperty(value = {"手机号"}, index = 11)
    private String phoneNumber;

    @ExcelProperty(value = {"手机号是否为柜面预留"}, index = 12)
    private String ifPhone;

    @ExcelProperty(value = {"测试编号"}, index = 13)
    private String NO;

}
