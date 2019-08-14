package com.shop.nearby.nearbyshop.model;

import lombok.Data;

@Data
public class Goods {
    private Integer id;
    private Integer shopId;
    private Double price;
    private Integer stock;
}
