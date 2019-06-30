package com.Bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Timestamp;

public class orders {
    public static final String table="orders";
    @JSONField(name ="id")
    private int id;
    @JSONField(name ="total_prices")
    private double total_prices;
    @JSONField(name ="total_quanity")
    private int total_quanity;
    @JSONField(name ="paystate")
    private int paystate;
    @JSONField(name ="ordertime")
    private Timestamp ordertime;
    @JSONField(name ="u_name")
    private String u_name;
    @JSONField(name ="consignee")
    private String consignee;
    @JSONField(name ="tel")
    private String tel;
    @JSONField(name ="address")
    private String address;

    public static String getTable() {
        return table;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal_prices() {
        return total_prices;
    }

    public void setTotal_prices(double total_prices) {
        this.total_prices = total_prices;
    }

    public int getTotal_quanity() {
        return total_quanity;
    }

    public void setTotal_quanity(int total_quanity) {
        this.total_quanity = total_quanity;
    }

    public int getPaystate() {
        return paystate;
    }

    public void setPaystate(int paystate) {
        this.paystate = paystate;
    }

    public Timestamp getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Timestamp ordertime) {
        this.ordertime = ordertime;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
