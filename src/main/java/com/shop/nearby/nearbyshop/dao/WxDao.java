package com.shop.nearby.nearbyshop.dao;

import com.shop.nearby.nearbyshop.model.Goods;
import com.shop.nearby.nearbyshop.model.GoodsAddress;
import com.shop.nearby.nearbyshop.model.Shop;
import com.shop.nearby.nearbyshop.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WxDao{
    //ToDo 这里到时候需要在服务器上设置mysql大小写不敏感
    //查询是否有当前openid
    @Select("select * from openIds where openid=#{openid}")
    UserInfo selectWxUserByOpenid(String openid);
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
    //获取店铺的收藏列表
    @Select("select si.* from shopInfo si,shopCollect sc " +
            "where sc.shopId=si.id and sc.openid = #{openid}")
    List<Shop> getShopCollectionListByOpenid(String openid);
    //上传店铺
    void insertShop(Shop shop);
    //根据openid更新用户是否是商户,是否是用户
    @Update("update openids set isSeller=1 where openid =#{openid}")
    void updateOpenid(String openid);
    //添加店铺的图片
    @Update("update shopInfo set pic=#{uuname} where id = #{id}")
    void updateShopId(Integer id, String uuname);
    //查询默认地址
    @Select("select id from goodsaddress where isdefault=1 and userid=#{openid}")
    Integer getDefaultAddressId(String openid);
    @Update("update openids set isUser=1,name=#{name},phonenum=#{phoneNum},address=#{address}" +
            ",province=#{province},city=#{city},county=#{county} where openid=#{userId}")
    void updateUserInfo(GoodsAddress goodsAddress);
    @Select("select isUser from openids where openid=#{openid}")
    Integer getIsUser(String openid);
    @Update("update openids set isUser=1,name=#{adminName},phonenum=#{phoneNum},address=#{addressDetails} " +
            " where openid=#{openid}")
    void updateOpenid2(Shop shop);
    @Select("SELECT shopname,(6371393 * ACOS(COS(RADIANS(#{lat2})) * COS(RADIANS(lat)) * " +
            "COS(RADIANS(#{lng2} - lng)) + SIN(RADIANS(#{lat2})) * SIN(RADIANS(lat)))) d " +
            "FROM shopinfo ORDER BY d ")
    List<Shop> getShopOrderByDistance(Double lng2, Double lat2);
    //附近1万米得热度最高两个店家
    @Select("SELECT shopname,(6371393 * ACOS(COS(RADIANS(#{lat2})) * COS(RADIANS(lat)) * " +
            "COS(RADIANS(#{lng2} - lng)) + SIN(RADIANS(#{lat2})) * SIN(RADIANS(lat)))) as d " +
            "FROM shopinfo having d<10000 order by hot desc limit 0,2")
    List<Shop> selectShopTwo(Double lat2, Double lng2);
    //热度最高的两个商品
    @Select("SELECT *,(6371393 * ACOS(COS(RADIANS(#{lat2})) * COS(RADIANS(lat)) * " +
            "COS(RADIANS(#{lng2} - lng)) + SIN(RADIANS(#{lat2})) * SIN(RADIANS(lat)))) d " +
            "FROM goods having d<10000 order by hot desc limit 0,2")
    List<Goods> selectHotTwo(Double lat2, Double lng2);
    @Select("SELECT *,(6371393 * ACOS(COS(RADIANS(#{lat2})) * COS(RADIANS(lat)) * " +
            "COS(RADIANS(#{lng2} - lng)) + SIN(RADIANS(#{lat2})) * SIN(RADIANS(lat)))) d " +
            "FROM goods having d<10000 and type='男装' order by hot desc limit 0,2")
    List<Goods> selectManTwo(Double lat2, Double lng2);
    @Select("SELECT *,(6371393 * ACOS(COS(RADIANS(#{lat2})) * COS(RADIANS(lat)) * " +
            "COS(RADIANS(#{lng2} - lng)) + SIN(RADIANS(#{lat2})) * SIN(RADIANS(lat)))) d " +
            "FROM goods having d<10000 and type='女装' order by hot desc limit 0,2")
    List<Goods> selectWomenTwo(Double lat2, Double lng2);
    @Select("SELECT *,(6371393 * ACOS(COS(RADIANS(#{lat2})) * COS(RADIANS(lat)) * " +
            "COS(RADIANS(#{lng2} - lng)) + SIN(RADIANS(#{lat2})) * SIN(RADIANS(lat)))) d " +
            "FROM goods having d<10000 and type='童装' order by hot desc limit 0,2")
    List<Goods> selectChildTwo(Double lat2, Double lng2);
    @Select("SELECT *,(6371393 * ACOS(COS(RADIANS(#{lat2})) * COS(RADIANS(lat)) * " +
            "COS(RADIANS(#{lng2} - lng)) + SIN(RADIANS(#{lat2})) * SIN(RADIANS(lat)))) d " +
            "FROM goods having d<10000 and type='鞋袜' order by hot desc limit 0,2")
    List<Goods> selectSocksShoesTwo(Double lat2, Double lng2);
    @Select("SELECT *,(6371393 * ACOS(COS(RADIANS(#{lat2})) * COS(RADIANS(lat)) * " +
            "COS(RADIANS(#{lng2} - lng)) + SIN(RADIANS(#{lat2})) * SIN(RADIANS(lat)))) d " +
            "FROM goods having d<10000 and type='帽子' order by hot desc limit 0,1")
    Goods selectHat(Double lat2, Double lng2);
    @Select("SELECT *,(6371393 * ACOS(COS(RADIANS(#{lat2})) * COS(RADIANS(lat)) * " +
            "COS(RADIANS(#{lng2} - lng)) + SIN(RADIANS(#{lat2})) * SIN(RADIANS(lat)))) d " +
            "FROM goods having d<10000 and type='箱包' order by hot desc limit 0,1")
    Goods selectPack(Double lat2, Double lng2);
    @Select("SELECT *,(6371393 * ACOS(COS(RADIANS(#{lat2})) * COS(RADIANS(lat)) * " +
            "COS(RADIANS(#{lng2} - lng)) + SIN(RADIANS(#{lat2})) * SIN(RADIANS(lat)))) d " +
            "FROM goods having d<10000 and type='孕婴' order by hot desc limit 0,2")
    List<Goods> selectBabyTwo(Double lat2, Double lng2);
    @Select("SELECT *,(6371393 * ACOS(COS(RADIANS(#{lat2})) * COS(RADIANS(lat)) * " +
            "COS(RADIANS(#{lng2} - lng)) + SIN(RADIANS(#{lat2})) * SIN(RADIANS(lat)))) d " +
            "FROM goods having d<10000 and type='配饰' order by hot desc limit 0,1")
    Goods selectPeishi(Double lat2, Double lng2);
    @Select("SELECT *,(6371393 * ACOS(COS(RADIANS(#{lat2})) * COS(RADIANS(lat)) * " +
            "COS(RADIANS(#{lng2} - lng)) + SIN(RADIANS(#{lat2})) * SIN(RADIANS(lat)))) d " +
            "FROM goods having d<10000 and type='内衣' order by hot desc limit 0,1")
    Goods selectUnderware(Double lat2, Double lng2);
    @Select("SELECT *,(6371393 * ACOS(COS(RADIANS(#{lat2})) * COS(RADIANS(lat)) * " +
            "COS(RADIANS(#{lng2} - lng)) + SIN(RADIANS(#{lat2})) * SIN(RADIANS(lat)))) d " +
            "FROM goods having d<100000 order by d,hot desc")
    List<Goods> getGoodsByHot(Double lat2, Double lng2);
    @Select("SELECT *,(6371393 * ACOS(COS(RADIANS(#{lat2})) * COS(RADIANS(lat)) * " +
            "COS(RADIANS(#{lng2} - lng)) + SIN(RADIANS(#{lat2})) * SIN(RADIANS(lat)))) d " +
            "FROM goods having d<100000 and type=#{type} order by hot desc")
    List<Goods> getGoodsByType(Double lat2, Double lng2, String type);
    @Select("select * from goods where id=#{id}")
    Goods getOneGoods(Integer id);
}
