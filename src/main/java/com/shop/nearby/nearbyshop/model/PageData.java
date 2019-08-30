package com.shop.nearby.nearbyshop.model;

import lombok.Data;

import java.util.List;

@Data
public class PageData<T> {
    private List<T> data;
    private Integer total;
}
