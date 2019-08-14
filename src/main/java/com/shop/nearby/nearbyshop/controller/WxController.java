package com.shop.nearby.nearbyshop.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shop.nearby.nearbyshop.dao.WxDao;
import com.shop.nearby.nearbyshop.model.*;
import com.shop.nearby.nearbyshop.utils.GetInterfaceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class WxController {

    @Value("${getSessionUrl}")
    private String getSessionUrl;
    @Autowired
    WxDao wxDao;

    /*
    获取openid
     */
    @PostMapping("getOpenId")
    public SessionAndOpenId getOpenId(HttpSession session, String code){
        System.out.println(code);
        StringBuilder sb= GetInterfaceUtil.getInterfaceRes(getSessionUrl+code);
        System.out.println(sb);
        JSONObject jsonObject= (JSONObject) JSON.parse(sb.toString());
        String openid=(String)jsonObject.get("openid");
        String session_key=(String)jsonObject.get("session_key");
        session.setAttribute(openid,session_key);//其实这里不用用openid作为key，使用一个写死的字符串就可以，
        //session中不会存在干扰
        if(null==wxDao.selectWxUserByOpenid(openid)){
            if("".equals(openid) || null==openid){
                return null;
            }
            wxDao.insertOpenid(openid);
        }
        return new SessionAndOpenId(session_key,openid);
    }
    //为什么要这么写，如果将前缀加在controller上，那么可以直接绕过前缀，访问接口，而拦截器按照前缀拦截就失去作用了
    @PostMapping("wx-api/test")
    public void test(){
        System.out.println(111);
    }

    /*
    添加收货地址
     */
    @PostMapping("wx-api/addCity")
    public ResponseEntity<?> addCity(GoodsAddress goodsAddress){
        if(null==goodsAddress.getUserId()) return ResponseEntity.ok(new BaseResponse(200,"请重新登录",null));
        if(null==goodsAddress.getAddress()) return ResponseEntity.ok(new BaseResponse(200,"详细地址不能为空",null));
        if(null==goodsAddress.getPhoneNum()) return ResponseEntity.ok(new BaseResponse(200,"请输入手机号",null));
        if(null==goodsAddress.getName()) return ResponseEntity.ok(new BaseResponse(200,"请输入姓名",null));
        if(null==goodsAddress.getProvince()) return ResponseEntity.ok(new BaseResponse(200,"请选择省",null));
        if(null==goodsAddress.getCity()) return ResponseEntity.ok(new BaseResponse(200,"请选择市",null));
        if(null==goodsAddress.getCounty()) return ResponseEntity.ok(new BaseResponse(200,"请选择县",null));
        wxDao.insertGoodsAddress(goodsAddress);
        return ResponseEntity.ok(new BaseResponse(200,"添加成功",null));
    }
    /*
    @Param: openid 客户端传过来openid， mapper中查询的时候字段为userid。
     */
    @PostMapping("wx-api/addressList")
    public ResponseEntity<?> addressList(String openid){
        List<GoodsAddress> list = wxDao.addressList(openid);
        return ResponseEntity.ok(new BaseResponse(200,"获取成功",list));
    }
    /*
    设置默认收货地址
     */
    @PostMapping("wx-api/setDefaultAddress")
    public ResponseEntity<?> setDefaultAddress(Integer id,String openid){
        wxDao.setDefaultAddress2(openid);//先把用户所有的地址设置成非默认的
        wxDao.setDefaultAddress(id);//然后把当前地址设置成默认的
        GoodsAddress goodsAddress = wxDao.getAddressById(id);
        return ResponseEntity.ok(new BaseResponse(200,"设置成功",goodsAddress));
    }
    /*
    上传商品
     */
    @PostMapping("wx-api/upGoods")
    public ResponseEntity<?> upGoods(Goods goods){

        return ResponseEntity.ok(new BaseResponse(200,"上传成功",null));
    }
}
