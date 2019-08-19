package com.aicp.icbc.webhook.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description:
 * @Author: 吴开云
 * @Date: 2019/8/19 0019
 * @Version： 1.0
 */
@Data
public class StagingInfoDto {
    @ExcelProperty(value = {"主叫号码"}, index = 0)
    private String phoneNumber;

    @ExcelProperty(value = {"身份证号"}, index = 1)
    private String idCardNumber;

    @ExcelProperty(value = {"卡号"}, index = 2)
    private String cardNumber;

    @ExcelProperty(value = {"密码"}, index = 3)
    private String password;

    @ExcelProperty(value = {"欠款金额"}, index = 4)
    private String overdraft;

    /**
     * 是否有欠款记录
     */
    private String recordFlag;

    /**
     * 密码是否匹配
     */
    private String passwordMatchFlag;

}
