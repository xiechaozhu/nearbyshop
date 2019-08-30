package com.shop.nearby.nearbyshop.model;

import lombok.Data;

@Data
public class UserInfo {
    private String openid;
    private Integer isSeller;//是否是商家默认0不是商家，只有在后台审核了才是1
    private Integer shopstats;//默认是0：正在审核，1是审核通过，2是审核失败。
    private Integer addressId;//用户默认收货地址id
}
