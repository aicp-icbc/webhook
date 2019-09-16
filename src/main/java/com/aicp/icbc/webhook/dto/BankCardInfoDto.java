package com.aicp.icbc.webhook.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @Description: 银行卡挂失流程-----银行卡信息dto -- 第三页
 * @Author: 吴开云
 * @Date: 2019/8/20 0020
 * @Version： 1.0
 */

public class BankCardInfoDto extends BaseRowModel {
    @ExcelProperty(value = {"姓名"}, index = 0)
    private String userName;

    @ExcelProperty(value = {"性别"}, index = 1)
    private String sex;

    @ExcelProperty(value = {"证件类型"}, index = 2)
    private String idCardType;

    @ExcelProperty(value = {"证件号码"}, index = 3)
    private String idNumber;

    @ExcelProperty(value = {"出生日期"}, index = 4)
    private String birthDate;

    @ExcelProperty(value = {"卡号"}, index = 5)
    private String cardNo;

    @ExcelProperty(value = {"卡/账户类型"}, index = 6)
    private String cardType;

    @ExcelProperty(value = {"开户日期"}, index = 7)
    private String openDate;

    @ExcelProperty(value = {"密码"}, index = 8)
    private String pwd;

    @ExcelProperty(value = {"联系电话"}, index = 9)
    private String phoneNo;

    @ExcelProperty(value = {"开户行"}, index = 10)
    private String openBank;

    @ExcelProperty(value = {"家庭地址"}, index = 11)
    private String address;

    @ExcelProperty(value = {"账户余额"}, index = 12)
    private String accountBalance;

    @ExcelProperty(value = {"账户状态"}, index = 13)
    private String accountStatus;

    @ExcelProperty(value = {"银行卡注册状态"}, index = 14)
    private String zhuCeFlag;

    @ExcelProperty(value = {"是否关联自动还款协议"}, index = 15)
    private String hkxyFalg;

    /**
     * 卡号后四位
     */
    private String carNoBackFour;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getZhuCeFlag() {
        return zhuCeFlag;
    }

    public void setZhuCeFlag(String zhuCeFlag) {
        this.zhuCeFlag = zhuCeFlag;
    }

    public String getHkxyFalg() {
        return hkxyFalg;
    }

    public void setHkxyFalg(String hkxyFalg) {
        this.hkxyFalg = hkxyFalg;
    }

    public String getCarNoBackFour() {
        return carNoBackFour;
    }

    public void setCarNoBackFour(String carNoBackFour) {
        this.carNoBackFour = carNoBackFour;
    }

    @Override
    public String toString() {
        return "BankCardInfoDto{" +
                "userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", idCardType='" + idCardType + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", cardType='" + cardType + '\'' +
                ", openDate='" + openDate + '\'' +
                ", pwd='" + pwd + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", openBank='" + openBank + '\'' +
                ", address='" + address + '\'' +
                ", accountBalance='" + accountBalance + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                ", zhuCeFlag='" + zhuCeFlag + '\'' +
                ", hkxyFalg='" + hkxyFalg + '\'' +
                ", carNoBackFour='" + carNoBackFour + '\'' +
                '}';
    }
}
