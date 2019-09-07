package com.shop.nearby.nearbyshop.model;

import lombok.Data;

@Data
public class WxUser extends BasePage{
    private String openid;
    private String name;
    private String phonenum;
    private String address;
    private String province;
    private String city;
    private String county;
    private Integer isSeller;//审核通过了才是seller
}
