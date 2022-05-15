package com.bjpn.bean;

import java.util.Objects;

public class ATM {
    private int atm_id;
    private String atm_name;
    private String atm_code;
    private String atm_pwd;
    private double atm_money;

    public ATM(){}

    public ATM(int atm_id, String atm_name, String atm_code, String atm_pwd, double atm_money) {
        this.atm_id = atm_id;
        this.atm_name = atm_name;
        this.atm_code = atm_code;
        this.atm_pwd = atm_pwd;
        this.atm_money = atm_money;
    }

    public int getAtm_id() {
        return atm_id;
    }

    public void setAtm_id(int atm_id) {
        this.atm_id = atm_id;
    }

    public String getAtm_name() {
        return atm_name;
    }

    public void setAtm_name(String atm_name) {
        this.atm_name = atm_name;
    }

    public String getAtm_code() {
        return atm_code;
    }

    public void setAtm_code(String atm_code) {
        this.atm_code = atm_code;
    }

    public String getAtm_pwd() {
        return atm_pwd;
    }

    public void setAtm_pwd(String atm_pwd) {
        this.atm_pwd = atm_pwd;
    }

    public double getAtm_money() {
        return atm_money;
    }

    public void setAtm_money(double atm_money) {
        this.atm_money = atm_money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ATM atm = (ATM) o;
        return atm_id == atm.atm_id && Double.compare(atm.atm_money, atm_money) == 0 && Objects.equals(atm_name, atm.atm_name) && Objects.equals(atm_code, atm.atm_code) && Objects.equals(atm_pwd, atm.atm_pwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atm_id, atm_name, atm_code, atm_pwd, atm_money);
    }

    @Override
    public String toString() {
        return "ATM{" +
                "atm_id=" + atm_id +
                ", atm_name='" + atm_name + '\'' +
                ", atm_code='" + atm_code + '\'' +
                ", atm_pwd='" + atm_pwd + '\'' +
                ", atm_money=" + atm_money +
                '}';
    }
}
