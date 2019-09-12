package com.shop.nearby.nearbyshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WxIndex {
    List<Shop> shop;
    //附近热度最高两个商品
    List<Goods> hot;
    //附近最高热度男装
    List<Goods> man;
    //附近最高热度女装
    List<Goods> women;
    //附近最高热度童装
    List<Goods> childTwo;
    //附近最高热度鞋袜
    List<Goods> shoesTwo;
    //附近最高热度帽子
    Goods hat;
    //热度最高箱包
    Goods pack;
    //附近最高热度配饰
    Goods peishi;
    //热度最高内衣
    Goods underware;
    //附近最高热度孕婴
    List<Goods> babyTwo;
}
