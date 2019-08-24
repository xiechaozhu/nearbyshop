package com.shop.nearby.nearbyshop.model;

import lombok.Data;

@Data
public class UserInfo {
    private GoodsAddress address;//默认收货地址
    private Integer isSeller;//是否是商家默认0不是商家，只有在后台审核了才是1
}
