package com.shop.nearby.nearbyshop.controller;

import com.shop.nearby.annotation.Admin;
import com.shop.nearby.annotation.Agent;
import com.shop.nearby.annotation.Shopper;
import com.shop.nearby.nearbyshop.dao.BackDao;
import com.shop.nearby.nearbyshop.model.*;
import com.shop.nearby.nearbyshop.services.BackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("back")
public class AdminController {
    @Autowired
    BackDao backDao;
    @Autowired
    BackService backService;
    //back和backlogin在拦截器中直接返回true所以不用加注解
    @RequestMapping("")
    public String login(HttpServletRequest request, Model model){
        String xx=request.getParameter("x");
        if(xx!=null){
            model.addAttribute("err_msg", "用户名或密码错误");
        }
        return "login";
    }

    @RequestMapping("backlogin")
    public String backLogin(HttpSession session, BackUser backUser, Model model, RedirectAttributes attributes){
        BackUser myuser = backDao.findUserByuser(backUser);
        if(null==myuser){
            System.out.println("用户名或密码错误");
            model.addAttribute("err_msg","用户名或密码错误");
            attributes.addAttribute("x",1);
            return "redirect:";
        }
        List<Power> powers = backDao.getPowers(myuser.getPower());
        myuser.setPowers(powers);
        //比如同时有两个人登录了系统，虽然都是myuser，但是它们存在不同的会话中，所以不会有干扰
        session.setAttribute("myuser",myuser);
        if(myuser.getPower()==1){
            //这里的作用是防止页面刷新提交表单
            return "redirect:toMain";
        }else if(myuser.getPower()==2){
            return "redirect:main";
        }
        return "redirect:shome";
    }
    //超级管理员主页
    @Admin("/back/toMain")
    @RequestMapping("toMain")
    public String toMain(){
        return "admin/home";
    }
    //进入用户管理页面
    @Admin("/back/userManage")
    @RequestMapping("userManage")
    public String userManage(){
        return "admin/userManage/userManage";
    }
    //ajax异步加载商铺数据
    @Admin("/back/users")
    @RequestMapping("users")
    @ResponseBody
    public PageData<WxUser> users(WxUser user){
        //如何获取用户信息：判断是不是商家，如果是商家直接从商家表取得信息，如果不是从默认地址取得信息
        //获取所有商家信息
        //获取所有不是商家的用户信息
        //算了，还是在添加商户或者添加默认地址的时候自动在openids这个表里面添加用户信息吧。要不然分页没法做。
        PageData<WxUser> pageData = new PageData();
        pageData.setData(backDao.findWxUsersByUser(user));
        pageData.setTotal(backDao.countWxUser(user));
        return pageData;
    }
    @Admin("/back/system")
    @RequestMapping("system")
    public String system(){
        return "admin/system/system";
    }
    //进入商铺管理页面
    @Admin("/back/shopManage")
    @RequestMapping("shopManage")
    public String shopManage(){
        return "admin/shopManage/shopManage";
    }
    //ajax异步加载商铺数据
    @Admin("/back/shops")
    @RequestMapping("shops")
    @ResponseBody
    public PageData<Shop> shops(Shop shop){
        PageData<Shop> pageData = new PageData();
        pageData.setData(backDao.findShopsByShop(shop));
        pageData.setTotal(backDao.countShop(shop));
        return pageData;
    }
    //超级管理员个人中心
    @Admin("/back/personCenter")
    @RequestMapping("personCenter")
    public String personCenter(){
        return "admin/personCenter/personCenter";
    }
    //超级管理员看到的订单列表
    @Admin("/back/orderList")
    @RequestMapping("orderList")
    public String orderList(){
        return "admin/orderList/orderList";
    }
    //ajax异步加载超级管理员订单数据
    @Admin("/back/adminorders")
    @RequestMapping("adminorders")
    @ResponseBody
    public PageData<BackOrder> adminorders(BackOrder order){
        PageData<BackOrder> pageData = new PageData();
        pageData.setData(backDao.findShopsByBackOrder(order));
        pageData.setTotal(backDao.countBackOrder(order));
        return pageData;
    }
    @Admin("/back/fundManage")
    //超级管理员进行资金管理的页面
    @RequestMapping("fundManage")
    public String fundManage(){
        return "admin/fundManage/fundManage";
    }
    //超级管理员管理区域
    @Admin("/back/areaManage")
    @RequestMapping("areaManage")
    public String areaManage(){
        return "admin/areaManage/areaManage";
    }

    //代理商主页
    @Agent("/back/main")
    @RequestMapping("main")
    public String main(){
        return "agent/main";
    }
    @Agent("/back/agentShopManage")
    @RequestMapping("agentShopManage")
    public String agentShopManage(){
        return "agent/shopManage/shopManage";
    }
    @Agent("/back/agentOrderList")
    @RequestMapping("agentOrderList")
    public String agentOrderList(){
        return "agent/orderList/orderList";
    }
    @Agent("/back/agentUserManage")
    @RequestMapping("agentUserManage")
    public String agentUserManage(){
        return "agent/userManage/userManage";
    }
    @Agent("/back/agentfundManage")
    @RequestMapping("agentfundManage")
    public String agentfundManage(){
        return "agent/fundManage/fundManage";
    }
    @Agent("/back/agentpersonCenter")
    @RequestMapping("agentpersonCenter")
    public String agentpersonCenter(){
        return "agent/personCenter/personCenter";
    }
    //店主主页
    @Shopper("/back/shome")
    @RequestMapping("shome")
    public String shome(){
        return "shopper/shome";
    }
    //店主商品管理
    @Shopper("/back/goodsManage")
    @RequestMapping("goodsManage")
    public String goodsManage(){
        return "shopper/goodsManage/goodsManage";
    }
    //店主订单列表
    @Shopper("/back/shopOrderList")
    @RequestMapping("shopOrderList")
    public String shopOrderList(){
        return "shopper/orderList/orderList";
    }
    //店主用户管理
    @Shopper("/back/shopUserManage")
    @RequestMapping("shopUserManage")
    public String shopUserManage(){
        return "shopper/userManage/userManage";
    }
    //店主个人中心
    @Shopper("/back/shopPersonCenter")
    @RequestMapping("shopPersonCenter")
    public String shopPersonCenter(){
        return "shopper/personCenter/personCenter";
    }
    @Agent("/back/logout")
    @Admin("/back/logout")
    @Shopper("/back/logout")
    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("myuser");
        return "login";
    }
}
