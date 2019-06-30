package com.Bean;

import com.alibaba.fastjson.annotation.JSONField;

public class orderitem {
    public static final String table="orderitem";
    @JSONField(name ="order_id")
    private int order_id;
    @JSONField(name ="product_id")
    private int product_id;
    @JSONField(name ="totalprice")
    private double totalprice;
    @JSONField(name ="num")
    private int num;

    public static String getTable() {
        return table;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
