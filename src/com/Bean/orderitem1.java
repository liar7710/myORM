package com.Bean;

import com.alibaba.fastjson.annotation.JSONField;

public class orderitem1 {
	public static final String table="orderitem left join products on orderitem.product_id=products.id";
    @JSONField(name ="order_id")
    private int order_id;
    @JSONField(name ="product_id")
    private int product_id;
    @JSONField(name ="price")
    private double totalprice;
    @JSONField(name ="num")
    private int num;
    @JSONField(name ="id")
    private int id;
    @JSONField(name ="name")
    private String name;
    @JSONField(name ="price")
    private double price;
    @JSONField(name ="category_1")
    private String category_1;
    @JSONField(name ="category_2")
    private String category_2;
    @JSONField(name ="pnum")
    private int pnum;
    @JSONField(name ="imgurl")
    private String imgurl;
    @JSONField(name ="status")
    private boolean status;
    @JSONField(name ="description")
    private String description;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory_1() {
        return category_1;
    }

    public void setCategory_1(String category_1) {
        this.category_1 = category_1;
    }

    public String getCategory_2() {
        return category_2;
    }

    public void setCategory_2(String category_2) {
        this.category_2 = category_2;
    }

    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
