package com.shop.nearby.nearbyshop.dao;

import com.shop.nearby.nearbyshop.model.GoodsAddress;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WxDao{
    //ToDo 这里到时候需要在服务器上设置mysql大小写不敏感
    //查询是否有当前openid
    @Select("select openid from openIds where openid=#{openid}")
    String selectWxUserByOpenid(String openid);
    //新添加openid
    @Insert("insert into openIds(openid) values (#{openid})")
    void insertOpenid(String openid);
    //插入收货地址
    @Insert("insert into goodsAddress(userId,name,phoneNum,province,city,county,address)" +
            "values(#{userId},#{name},#{phoneNum},#{province},#{city},#{county},#{address})")
    void insertGoodsAddress(GoodsAddress goodsAddress);
    //根据openid查询收货地址
    @Select("select * from goodsAddress where userId = #{openid} ")
    List<GoodsAddress> addressList(String openid);
    //设置默认收货地址
    @Update("update goodsAddress set isDefault=1 where id =#{id}")
    void setDefaultAddress(Integer id);
    //根据openid更新收货地址为非默认地址
    @Update("update goodsAddress set isDefault=0 where userId =#{openid}")
    void setDefaultAddress2(String openid);
    //根据id查询收货地址
    @Select("select * from goodsAddress where id =#{id}")
    GoodsAddress getAddressById(Integer id);
}
