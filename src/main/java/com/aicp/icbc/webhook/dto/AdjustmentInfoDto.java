package com.aicp.icbc.webhook.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @Description: 一键调额流程 数据DTO
 * @Author: 吴开云
 * @Date: 2019/9/2 0002
 * @Version： 1.0
 */
public class AdjustmentInfoDto extends BaseRowModel {
    @ExcelProperty(value = {"姓名"}, index = 0)
    private String name;

    @ExcelProperty(value = {"证件号码"}, index = 1)
    private String IDCardNumber;

    @ExcelProperty(value = {"卡号"}, index = 2)
    private String cardNum;

    @ExcelProperty(value = {"密码"}, index = 3)
    private String password1;

    @ExcelProperty(value = {"当前卡片额度"}, index = 4)
    private Integer sDefineCredit;

    @ExcelProperty(value = {"最高可调整的额度"}, index = 5)
    private Integer quotaMax;

    @ExcelProperty(value = {"实时生效最小值"}, index = 6)
    private Integer quotaMin;

    @ExcelProperty(value = {"系统身份"}, index = 7)
    private String systemIdentity;

    @ExcelProperty(value = {"卡归属"}, index = 8)
    private String cardowner;

    @ExcelProperty(value = {"卡类型"}, index = 9)
    private String cardType;

    @ExcelProperty(value = {"卡类型1"}, index = 10)
    private String cardType1;

    @ExcelProperty(value = {"卡类型2"}, index = 11)
    private String cardType2;

    @ExcelProperty(value = {"卡级别"}, index = 12)
    private String cardJiBie;

    @ExcelProperty(value = {"备注"}, index = 13)
    private String remarks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIDCardNumber() {
        return IDCardNumber;
    }

    public void setIDCardNumber(String IDCardNumber) {
        this.IDCardNumber = IDCardNumber;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public Integer getsDefineCredit() {
        return sDefineCredit;
    }

    public void setsDefineCredit(Integer sDefineCredit) {
        this.sDefineCredit = sDefineCredit;
    }

    public Integer getQuotaMax() {
        return quotaMax;
    }

    public void setQuotaMax(Integer quotaMax) {
        this.quotaMax = quotaMax;
    }

    public Integer getQuotaMin() {
        return quotaMin;
    }

    public void setQuotaMin(Integer quotaMin) {
        this.quotaMin = quotaMin;
    }

    public String getSystemIdentity() {
        return systemIdentity;
    }

    public void setSystemIdentity(String systemIdentity) {
        this.systemIdentity = systemIdentity;
    }

    public String getCardowner() {
        return cardowner;
    }

    public void setCardowner(String cardowner) {
        this.cardowner = cardowner;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardType1() {
        return cardType1;
    }

    public void setCardType1(String cardType1) {
        this.cardType1 = cardType1;
    }

    public String getCardType2() {
        return cardType2;
    }

    public void setCardType2(String cardType2) {
        this.cardType2 = cardType2;
    }

    public String getCardJiBie() {
        return cardJiBie;
    }

    public void setCardJiBie(String cardJiBie) {
        this.cardJiBie = cardJiBie;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "AdjustmentInfoDto{" +
                "name='" + name + '\'' +
                ", IDCardNumber='" + IDCardNumber + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", password1=" + password1 +
                ", sDefineCredit=" + sDefineCredit +
                ", quotaMax=" + quotaMax +
                ", quotaMin=" + quotaMin +
                ", systemIdentity='" + systemIdentity + '\'' +
                ", cardowner='" + cardowner + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cardType1='" + cardType1 + '\'' +
                ", cardType2='" + cardType2 + '\'' +
                ", cardJiBie='" + cardJiBie + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
