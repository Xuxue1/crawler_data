package com.xuxue.crawler;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public class Model {
    private String plateformName;
    private String faren;
    private String onLineTime;
    private String money;
    private String phone;
    private String address;
    private String company;


    public String getPlateformName() {
        return plateformName;
    }

    public void setPlateformName(String plateformName) {
        this.plateformName = plateformName;
    }

    public String getFaren() {
        return faren;
    }

    public void setFaren(String faren) {
        this.faren = faren;
    }

    public String getOnLineTime() {
        return onLineTime;
    }

    public void setOnLineTime(String onLineTime) {
        this.onLineTime = onLineTime;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Model{" +
                "plateformName='" + plateformName + '\'' +
                ", faren='" + faren + '\'' +
                ", onLineTime='" + onLineTime + '\'' +
                ", money='" + money + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
