package com.shop.nearby.nearbyshop.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
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
}
