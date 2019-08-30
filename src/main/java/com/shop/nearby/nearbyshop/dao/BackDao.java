package com.shop.nearby.nearbyshop.dao;

import com.shop.nearby.nearbyshop.model.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BackDao {
    //查询用户
    @Select("select * from backuser where username=#{username} and pwd=#{pwd}")
    BackUser findUserByuser(BackUser backUser);

    //分页查询商店
    List<Shop> findShopsByShop(@Param("shop") Shop shop);
    //查询商店总数
    Integer countShop(@Param("shop") Shop shop);
    //查询微信用户
    List<WxUser> findWxUsersByUser(@Param("user")WxUser user);
    //微信用户总数
    Integer countWxUser(@Param("user")WxUser user);
    @Select("select * from roleandpower where role=#{power}")
    List<Power> getPowers(Integer power);
    //分页查询超级管理员的订单
    List<BackOrder> findShopsByBackOrder(@Param("order")BackOrder order);
        //查询超级管理员的所有订单数
    Integer countBackOrder(@Param("order")BackOrder order);
}
