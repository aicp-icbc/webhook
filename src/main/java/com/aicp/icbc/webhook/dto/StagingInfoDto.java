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

    /**
     * 账单分期卡号后四位
     */
    private String zdCardNumberFour;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegisterFlag() {
        return registerFlag;
    }

    public void setRegisterFlag(String registerFlag) {
        this.registerFlag = registerFlag;
    }

    public String getMainOrElse() {
        return mainOrElse;
    }

    public void setMainOrElse(String mainOrElse) {
        this.mainOrElse = mainOrElse;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(String overdraft) {
        this.overdraft = overdraft;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getInstalment() {
        return instalment;
    }

    public void setInstalment(String instalment) {
        this.instalment = instalment;
    }

    public String getFirstRepaymentDate() {
        return firstRepaymentDate;
    }

    public void setFirstRepaymentDate(String firstRepaymentDate) {
        this.firstRepaymentDate = firstRepaymentDate;
    }

    public String getMaxInstalment() {
        return maxInstalment;
    }

    public void setMaxInstalment(String maxInstalment) {
        this.maxInstalment = maxInstalment;
    }

    public String getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(String remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public String getServiceChargeFate3() {
        return serviceChargeFate3;
    }

    public void setServiceChargeFate3(String serviceChargeFate3) {
        this.serviceChargeFate3 = serviceChargeFate3;
    }

    public String getServiceCharge3() {
        return serviceCharge3;
    }

    public void setServiceCharge3(String serviceCharge3) {
        this.serviceCharge3 = serviceCharge3;
    }

    public String getFirstRepaymentMoney3() {
        return firstRepaymentMoney3;
    }

    public void setFirstRepaymentMoney3(String firstRepaymentMoney3) {
        this.firstRepaymentMoney3 = firstRepaymentMoney3;
    }

    public String getRepaymentMoney3() {
        return repaymentMoney3;
    }

    public void setRepaymentMoney3(String repaymentMoney3) {
        this.repaymentMoney3 = repaymentMoney3;
    }

    public String getServiceChargeFate6() {
        return serviceChargeFate6;
    }

    public void setServiceChargeFate6(String serviceChargeFate6) {
        this.serviceChargeFate6 = serviceChargeFate6;
    }

    public String getServiceCharge6() {
        return serviceCharge6;
    }

    public void setServiceCharge6(String serviceCharge6) {
        this.serviceCharge6 = serviceCharge6;
    }

    public String getFirstRepaymentMoney6() {
        return firstRepaymentMoney6;
    }

    public void setFirstRepaymentMoney6(String firstRepaymentMoney6) {
        this.firstRepaymentMoney6 = firstRepaymentMoney6;
    }

    public String getRepaymentMoney6() {
        return repaymentMoney6;
    }

    public void setRepaymentMoney6(String repaymentMoney6) {
        this.repaymentMoney6 = repaymentMoney6;
    }

    public String getServiceChargeFate9() {
        return serviceChargeFate9;
    }

    public void setServiceChargeFate9(String serviceChargeFate9) {
        this.serviceChargeFate9 = serviceChargeFate9;
    }

    public String getServiceCharge9() {
        return serviceCharge9;
    }

    public void setServiceCharge9(String serviceCharge9) {
        this.serviceCharge9 = serviceCharge9;
    }

    public String getFirstRepaymentMoney9() {
        return firstRepaymentMoney9;
    }

    public void setFirstRepaymentMoney9(String firstRepaymentMoney9) {
        this.firstRepaymentMoney9 = firstRepaymentMoney9;
    }

    public String getRepaymentMoney9() {
        return repaymentMoney9;
    }

    public void setRepaymentMoney9(String repaymentMoney9) {
        this.repaymentMoney9 = repaymentMoney9;
    }

    public String getServiceChargeFate12() {
        return serviceChargeFate12;
    }

    public void setServiceChargeFate12(String serviceChargeFate12) {
        this.serviceChargeFate12 = serviceChargeFate12;
    }

    public String getServiceCharge12() {
        return serviceCharge12;
    }

    public void setServiceCharge12(String serviceCharge12) {
        this.serviceCharge12 = serviceCharge12;
    }

    public String getFirstRepaymentMoney12() {
        return firstRepaymentMoney12;
    }

    public void setFirstRepaymentMoney12(String firstRepaymentMoney12) {
        this.firstRepaymentMoney12 = firstRepaymentMoney12;
    }

    public String getRepaymentMoney12() {
        return repaymentMoney12;
    }

    public void setRepaymentMoney12(String repaymentMoney12) {
        this.repaymentMoney12 = repaymentMoney12;
    }

    public String getServiceChargeFate18() {
        return serviceChargeFate18;
    }

    public void setServiceChargeFate18(String serviceChargeFate18) {
        this.serviceChargeFate18 = serviceChargeFate18;
    }

    public String getServiceCharge18() {
        return serviceCharge18;
    }

    public void setServiceCharge18(String serviceCharge18) {
        this.serviceCharge18 = serviceCharge18;
    }

    public String getFirstRepaymentMoney18() {
        return firstRepaymentMoney18;
    }

    public void setFirstRepaymentMoney18(String firstRepaymentMoney18) {
        this.firstRepaymentMoney18 = firstRepaymentMoney18;
    }

    public String getRepaymentMoney18() {
        return repaymentMoney18;
    }

    public void setRepaymentMoney18(String repaymentMoney18) {
        this.repaymentMoney18 = repaymentMoney18;
    }

    public String getServiceChargeFate24() {
        return serviceChargeFate24;
    }

    public void setServiceChargeFate24(String serviceChargeFate24) {
        this.serviceChargeFate24 = serviceChargeFate24;
    }

    public String getServiceCharge24() {
        return serviceCharge24;
    }

    public void setServiceCharge24(String serviceCharge24) {
        this.serviceCharge24 = serviceCharge24;
    }

    public String getFirstRepaymentMoney24() {
        return firstRepaymentMoney24;
    }

    public void setFirstRepaymentMoney24(String firstRepaymentMoney24) {
        this.firstRepaymentMoney24 = firstRepaymentMoney24;
    }

    public String getRepaymentMoney24() {
        return repaymentMoney24;
    }

    public void setRepaymentMoney24(String repaymentMoney24) {
        this.repaymentMoney24 = repaymentMoney24;
    }

    public String getServiceChargeFate() {
        return serviceChargeFate;
    }

    public void setServiceChargeFate(String serviceChargeFate) {
        this.serviceChargeFate = serviceChargeFate;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getFirstRepaymentMoney() {
        return firstRepaymentMoney;
    }

    public void setFirstRepaymentMoney(String firstRepaymentMoney) {
        this.firstRepaymentMoney = firstRepaymentMoney;
    }

    public String getRepaymentMoney() {
        return repaymentMoney;
    }

    public void setRepaymentMoney(String repaymentMoney) {
        this.repaymentMoney = repaymentMoney;
    }

    public String getZdCardNumberFour() {
        return zdCardNumberFour;
    }

    public void setZdCardNumberFour(String zdCardNumberFour) {
        this.zdCardNumberFour = zdCardNumberFour;
    }

    @Override
    public String toString() {
        return "StagingInfoDto{" +
                "userName='" + userName + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", password='" + password + '\'' +
                ", registerFlag='" + registerFlag + '\'' +
                ", mainOrElse='" + mainOrElse + '\'' +
                ", cardType='" + cardType + '\'' +
                ", overdraft='" + overdraft + '\'' +
                ", currency='" + currency + '\'' +
                ", instalment='" + instalment + '\'' +
                ", firstRepaymentDate='" + firstRepaymentDate + '\'' +
                ", maxInstalment='" + maxInstalment + '\'' +
                ", remainingAmount='" + remainingAmount + '\'' +
                ", serviceChargeFate3='" + serviceChargeFate3 + '\'' +
                ", serviceCharge3='" + serviceCharge3 + '\'' +
                ", firstRepaymentMoney3='" + firstRepaymentMoney3 + '\'' +
                ", repaymentMoney3='" + repaymentMoney3 + '\'' +
                ", serviceChargeFate6='" + serviceChargeFate6 + '\'' +
                ", serviceCharge6='" + serviceCharge6 + '\'' +
                ", firstRepaymentMoney6='" + firstRepaymentMoney6 + '\'' +
                ", repaymentMoney6='" + repaymentMoney6 + '\'' +
                ", serviceChargeFate9='" + serviceChargeFate9 + '\'' +
                ", serviceCharge9='" + serviceCharge9 + '\'' +
                ", firstRepaymentMoney9='" + firstRepaymentMoney9 + '\'' +
                ", repaymentMoney9='" + repaymentMoney9 + '\'' +
                ", serviceChargeFate12='" + serviceChargeFate12 + '\'' +
                ", serviceCharge12='" + serviceCharge12 + '\'' +
                ", firstRepaymentMoney12='" + firstRepaymentMoney12 + '\'' +
                ", repaymentMoney12='" + repaymentMoney12 + '\'' +
                ", serviceChargeFate18='" + serviceChargeFate18 + '\'' +
                ", serviceCharge18='" + serviceCharge18 + '\'' +
                ", firstRepaymentMoney18='" + firstRepaymentMoney18 + '\'' +
                ", repaymentMoney18='" + repaymentMoney18 + '\'' +
                ", serviceChargeFate24='" + serviceChargeFate24 + '\'' +
                ", serviceCharge24='" + serviceCharge24 + '\'' +
                ", firstRepaymentMoney24='" + firstRepaymentMoney24 + '\'' +
                ", repaymentMoney24='" + repaymentMoney24 + '\'' +
                ", serviceChargeFate='" + serviceChargeFate + '\'' +
                ", serviceCharge='" + serviceCharge + '\'' +
                ", firstRepaymentMoney='" + firstRepaymentMoney + '\'' +
                ", repaymentMoney='" + repaymentMoney + '\'' +
                ", zdCardNumberFour='" + zdCardNumberFour + '\'' +
                '}';
    }
}
