package com.shop.nearby.nearbyshop.model;

import lombok.Data;

@Data
public class Shop  extends BasePage{
    private String openid;//店主openid
    private Integer id;
    private String touxiang;
    private String shopName;
    private String adminName;
    private String phoneNum;
    private String addressDetails;
    private String lng;
    private String lat;
    private String code;
    private String pic;
    private Integer status;
}
