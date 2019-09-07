package com.shop.nearby.nearbyshop.model;

import lombok.Data;

@Data
public class GoodsParam extends BasePage{
    private Integer id;
    private Integer shopid;
    private String type;
    private String subclass;
    private String paramone;
    private String onevalue;
    private String paramtwo;
    private String twovalue;
    private String paramthree;
    private String threevalue;
}
