package com.shop.nearby.nearbyshop.model;

import lombok.Data;

@Data
public class GoodsAddress {
    private Integer id;
    private String userId;
    private String name;
    private String phoneNum;
    private String province;
    private String city;
    private String county;
    private String address;
}
