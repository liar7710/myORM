package com.Bean;

import com.alibaba.fastjson.annotation.JSONField;

public class products {
    public static final String table="products";
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

    public void setCategory_1(String category) {
        this.category_1 = category;
    }
    public String getCategory_2() {
        return category_2;
    }

    public void setCategory_2(String category) {
        this.category_2 = category;
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
