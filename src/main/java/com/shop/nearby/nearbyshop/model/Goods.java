package com.shop.nearby.nearbyshop.model;

import lombok.Data;
import lombok.ToString;


public class Goods extends BasePage{
    private Integer id;
    private Integer shopid;
    private String name;
    private Double price;
    private Integer stock;
    private String pic;
    private String content;
    private String type;
    private String subclass;
    private String paramone;
    private String onevalue;
    private String paramtwo;
    private String twovalue;
    private String paramthree;
    private String threevalue;
    private Double lat;
    private Double lng;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubclass() {
        return subclass;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

    public String getParamone() {
        return paramone;
    }

    public void setParamone(String paramone) {
        this.paramone = paramone;
    }

    public String getOnevalue() {
        return onevalue;
    }

    public void setOnevalue(String onevalue) {
        this.onevalue = onevalue;
    }

    public String getParamtwo() {
        return paramtwo;
    }

    public void setParamtwo(String paramtwo) {
        this.paramtwo = paramtwo;
    }

    public String getTwovalue() {
        return twovalue;
    }

    public void setTwovalue(String twovalue) {
        this.twovalue = twovalue;
    }

    public String getParamthree() {
        return paramthree;
    }

    public void setParamthree(String paramthree) {
        this.paramthree = paramthree;
    }

    public String getThreevalue() {
        return threevalue;
    }

    public void setThreevalue(String threevalue) {
        this.threevalue = threevalue;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
