package com.Bean;

import com.alibaba.fastjson.annotation.JSONField;
public class addr {
    public static final String table="addr";
    @JSONField(name ="id")
    private int id;
    @JSONField(name ="address")
    private String address;
    @JSONField(name ="tel")
    private String tel;
    @JSONField(name ="consignee")
    private String consignee;
    @JSONField(name ="u_name")
    private String u_name;

    public static String getTable() {
        return table;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }
}
