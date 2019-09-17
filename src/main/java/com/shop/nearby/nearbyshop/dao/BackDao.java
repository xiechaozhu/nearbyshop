package com.shop.nearby.nearbyshop.dao;

import com.shop.nearby.nearbyshop.model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
    @Insert("insert into types (shopid,type,subclass,paramone,onevalue,paramtwo,twovalue," +
            "paramthree,threevalue) values (#{shopid},#{type},#{subclass},#{paramone},#{onevalue}" +
            ",#{paramtwo},#{twovalue},#{paramthree},#{threevalue})")
    void addGoodsParams(GoodsParam goodsParam);
    @Select("select id from shopinfo where openid =#{id}")
    Integer getShopid(String id);
//
//    List<GoodsParam> findParamsByGoodsParam(@Param("param")GoodsParam param);
//
//    Integer countParams(@Param("param")GoodsParam param);
//    @Select("select * from types where shopid=#{shopid} and type = #{type} and subclass = #{subclass}")
//    GoodsParam getparams(Integer shopid,String type,String subclass);
    @Insert("insert into goods (name,pic,content,shopid,price,stock,type,subclass," +
            "paramone,onevalue,paramtwo,twovalue,paramthree,threevalue,lat,lng) values (" +
            "#{name},#{pic},#{content},#{shopid},#{price},#{stock},#{type},#{subclass}" +
            ",#{paramone},#{onevalue},#{paramtwo},#{twovalue},#{paramthree},#{threevalue},#{lan},#{lng})")
    void saveGood(Goods goods);

    //分页查询商品
    List<Goods> findGoodsByGoods(@Param("goods")Goods goods);
    //条件查询商品数量
    Integer countGoods(@Param("goods")Goods goods);
    //审核通过
    @Update("update shopinfo set status=1 where id=#{id}")
    void checkpass(Integer id);
    //审核通过
    @Update("update openids set isSeller=2,shopstats=1 where openid=#{openid}")
    void checkpass2(String openid);
    //审核失败
    @Update("update shopinfo set status=2 where id=#{id}")
    void checkdeny(Integer id);
    @Update("update openids set isSeller=0,shopstats=2 where openid=#{openid}")
    void checkdeny2(String openid);
    //分页查询代理商
    List<BackOrder> findAreaAgentByAreaAgent(@Param("areaAgent")AreaAgent areaAgent);
    //代理商总数
    Integer countAreaAgent(@Param("areaAgent")AreaAgent areaAgent);
    @Update("update backuser set pwd = #{newpwd} where username=#{username}")
    void changepwd(String username, String newpwd);
    @Select("select * from shopinfo where id =#{shopid}")
    Shop geShopByid(int shopid);
    //添加店主后台账户
    @Insert("insert into backuser(username,pwd,power,openid) values (#{username},#{pwd},3,#{openid})")
    void addBackUser(BackUser backUser);
    @Insert("insert into backuser(username,pwd,power,phone,name,area) values(#{username},#{pwd},2,#{phone}," +
            "#{name},#{area})")
    void addAreaAgent(AreaAgent areaAgent);
    @Select("select * from shopinfo where id=#{shopid}")
    Shop getShopByid(int shopid);

    //

}
