package com.shop.nearby.nearbyshop.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
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
    private Date createtime;
    private String uname;
    private String pwd;
}
