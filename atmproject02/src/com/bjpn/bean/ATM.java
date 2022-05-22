package com.bjpn.bean;

import java.util.Objects;

public class ATM {
    private int atmId;
    private String atmName;//用户名
    private String atmCode;//卡号  卡号唯一
    private String atmPwd;//密码
    private double atmMoney;//卡上余额

    public int getAtmId() {
        return atmId;
    }

    public void setAtmId(int atmId) {
        this.atmId = atmId;
    }

    public String getAtmName() {
        return atmName;
    }

    public void setAtmName(String atmName) {
        this.atmName = atmName;
    }

    public String getAtmCode() {
        return atmCode;
    }

    public void setAtmCode(String atmCode) {
        this.atmCode = atmCode;
    }

    public String getAtmPwd() {
        return atmPwd;
    }

    public void setAtmPwd(String atmPwd) {
        this.atmPwd = atmPwd;
    }

    public double getAtmMoney() {
        return atmMoney;
    }

    public void setAtmMoney(double atmMoney) {
        this.atmMoney = atmMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ATM atm = (ATM) o;
        return atmId == atm.atmId && Double.compare(atm.atmMoney, atmMoney) == 0 && Objects.equals(atmName, atm.atmName) && Objects.equals(atmCode, atm.atmCode) && Objects.equals(atmPwd, atm.atmPwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atmId, atmName, atmCode, atmPwd, atmMoney);
    }

    public ATM(int atmId, String atmName, String atmCode, String atmPwd, double atmMoney) {
        this.atmId = atmId;
        this.atmName = atmName;
        this.atmCode = atmCode;
        this.atmPwd = atmPwd;
        this.atmMoney = atmMoney;
    }
    public ATM(){}

    @Override
    public String toString() {
        return "ATM{" +
                "atmId=" + atmId +
                ", atmName='" + atmName + '\'' +
                ", atmCode='" + atmCode + '\'' +
                ", atmPwd='" + atmPwd + '\'' +
                ", atmMoney=" + atmMoney +
                '}';
    }
}
