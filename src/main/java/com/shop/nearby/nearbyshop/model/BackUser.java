package com.shop.nearby.nearbyshop.model;

import lombok.Data;

import java.util.List;

@Data
public class BackUser {
    private Integer id;
    private String username;
    private String pwd;
    private Integer power;
    private List<Power> powers;
}
