package com.shop.nearby.nearbyshop.model;

import lombok.Data;

import java.util.List;

@Data
public class BackUser {
    private String openid;
    private String username;
    private String pwd;
    private Integer power;//角色1是管理员，2代理商，3商店店主
    private List<Power> powers;//权限列表
}
