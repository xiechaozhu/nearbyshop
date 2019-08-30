package com.shop.nearby.nearbyshop.model;

import lombok.Data;

import java.util.Date;

@Data
public class BackOrder extends BasePage{
    private Integer id;
    private String goodsname;
    private Integer num;
    private Date time;
    private Integer status;
    private Double money;
    private String username;
    private String userphone;
    private String province;
    private String city;
    private String county;
    private String address;
}
