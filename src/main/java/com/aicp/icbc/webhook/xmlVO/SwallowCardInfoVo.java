package com.aicp.icbc.webhook.xmlVO;

/**
 * @Description: 行方提供的接口字段
 * @Author: 吴开云
 * @Date: 2019/9/6 0006
 * @Version： 1.0
 */
public class SwallowCardInfoVo {

    /**
     * 卡号
     */
    private String CARDNO;
    /**
     *用户名
     */
    private String NAME;
    /**
     *证件号码
     */
    private String IDNUMBER;
    /**
     * 联系电话
     */
    private String CONTACT_NUMBER;
    /**
     * 机具编号
     */
    private String EXTREMELYNO;
    /**
     * 机具位置
     */
    private String EXTREMELY_LOCATION;
    /**
     * 管理网点
     */
    private String MANAGEMENT_OUTLET;
    /**
     * 吞卡时间
     */
    private String SWALLOW_TIME;

    public String getCARDNO() {
        return CARDNO;
    }

    public void setCARDNO(String CARDNO) {
        this.CARDNO = CARDNO;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getIDNUMBER() {
        return IDNUMBER;
    }

    public void setIDNUMBER(String IDNUMBER) {
        this.IDNUMBER = IDNUMBER;
    }

    public String getCONTACT_NUMBER() {
        return CONTACT_NUMBER;
    }

    public void setCONTACT_NUMBER(String CONTACT_NUMBER) {
        this.CONTACT_NUMBER = CONTACT_NUMBER;
    }

    public String getEXTREMELYNO() {
        return EXTREMELYNO;
    }

    public void setEXTREMELYNO(String EXTREMELYNO) {
        this.EXTREMELYNO = EXTREMELYNO;
    }

    public String getEXTREMELY_LOCATION() {
        return EXTREMELY_LOCATION;
    }

    public void setEXTREMELY_LOCATION(String EXTREMELY_LOCATION) {
        this.EXTREMELY_LOCATION = EXTREMELY_LOCATION;
    }

    public String getMANAGEMENT_OUTLET() {
        return MANAGEMENT_OUTLET;
    }

    public void setMANAGEMENT_OUTLET(String MANAGEMENT_OUTLET) {
        this.MANAGEMENT_OUTLET = MANAGEMENT_OUTLET;
    }

    public String getSWALLOW_TIME() {
        return SWALLOW_TIME;
    }

    public void setSWALLOW_TIME(String SWALLOW_TIME) {
        this.SWALLOW_TIME = SWALLOW_TIME;
    }

    @Override
    public String toString() {
        return "SwallowCardInfoVo{" +
                "CARDNO='" + CARDNO + '\'' +
                ", NAME='" + NAME + '\'' +
                ", IDNUMBER='" + IDNUMBER + '\'' +
                ", CONTACT_NUMBER='" + CONTACT_NUMBER + '\'' +
                ", EXTREMELYNO='" + EXTREMELYNO + '\'' +
                ", EXTREMELY_LOCATION='" + EXTREMELY_LOCATION + '\'' +
                ", MANAGEMENT_OUTLET='" + MANAGEMENT_OUTLET + '\'' +
                ", SWALLOW_TIME='" + SWALLOW_TIME + '\'' +
                '}';
    }
}
