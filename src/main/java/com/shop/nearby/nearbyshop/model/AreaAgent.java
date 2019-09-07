package com.shop.nearby.nearbyshop.model;

import lombok.Data;

import java.util.Date;
@Data
public class AreaAgent extends BasePage{
    private String username;
    private String pwd;
    private String phone;
    private String name;
    private String area;
    private Date createtime;
}
