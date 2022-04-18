package com.example.mongodbrealmcourse.data.model;

import io.realm.RealmObject;

public class Receipts  {
    private String name;
    private  String price;
    private String imgUrl;

    public Receipts(String name,String price,String imgUrl)
    {
        this.imgUrl = imgUrl;
        this.name = name;
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Receipts{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
