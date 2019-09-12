package com.shop.nearby.nearbyshop.controller;

import com.shop.nearby.annotation.Admin;
import com.shop.nearby.annotation.Agent;
import com.shop.nearby.annotation.Shopper;
import com.shop.nearby.nearbyshop.dao.BackDao;
import com.shop.nearby.nearbyshop.model.*;
import com.shop.nearby.nearbyshop.services.BackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.*;

@Controller
@RequestMapping("back")
public class AdminController {
    @Autowired
    BackDao backDao;
    @Autowired
    BackService backService;

    @Value("${filepath}")
    private String filepath;

    //back和backlogin在拦截器中直接返回true所以不用加注解
    @RequestMapping("")
    public String login(HttpServletRequest request, Model model) {
        String xx = request.getParameter("x");
        if (xx != null) {
            model.addAttribute("err_msg", "用户名或密码错误");
        }
        return "login";
    }

    @RequestMapping("backlogin")
    public String backLogin(HttpSession session, BackUser backUser, Model model, RedirectAttributes attributes) {
        BackUser myuser = backDao.findUserByuser(backUser);
        if (null == myuser) {
            System.out.println("用户名或密码错误");
            model.addAttribute("err_msg", "用户名或密码错误");
            attributes.addAttribute("x", 1);
            return "redirect:";
        }
        //获取首页权限列表
        List<Power> powers = backDao.getPowers(myuser.getPower());
        myuser.setPowers(powers);
        //比如同时有两个人登录了系统，虽然都是myuser，但是它们存在不同的会话中，所以不会有干扰
        session.setAttribute("myuser", myuser);
        if (myuser.getPower() == 1) {
            //这里的作用是防止页面刷新提交表单
            return "redirect:toMain";
        } else if (myuser.getPower() == 2) {
            return "redirect:main";
        }
        //获取店长才有openid字段，根据openid获取店铺id
        Integer shopid = backDao.getShopid(myuser.getOpenid());
        session.setAttribute("shopid", shopid);
        return "redirect:shome";
    }

    //超级管理员主页
    @Admin("/back/toMain")
    @RequestMapping("toMain")
    public String toMain() {
        return "admin/home";
    }

    //进入用户管理页面
    @Admin("/back/userManage")
    @RequestMapping("userManage")
    public String userManage() {
        return "admin/userManage/userManage";
    }

    //ajax异步加载商铺数据
    @Admin("/back/users")
    @RequestMapping("users")
    @ResponseBody
    public PageData<WxUser> users(WxUser user) {
        //如何获取用户信息：判断是不是商家，如果是商家直接从商家表取得信息，如果不是从默认地址取得信息
        //获取所有商家信息
        //获取所有不是商家的用户信息
        //算了，还是在添加商户或者添加默认地址的时候自动在openids这个表里面添加用户信息吧。要不然分页没法做。
        PageData<WxUser> pageData = new PageData<>();
        pageData.setData(backDao.findWxUsersByUser(user));
        pageData.setTotal(backDao.countWxUser(user));
        return pageData;
    }

    @Admin("/back/system")
    @RequestMapping("system")
    public String system() {
        return "admin/system/system";
    }

    @Admin("/back/changepassword")
    @RequestMapping("changepassword")
    public String changepassword(HttpSession session, String pwd, String newpwd, Model model) {
        BackUser backUser = (BackUser) session.getAttribute("myuser");
        if (!backUser.getPwd().equals(pwd)) {
            model.addAttribute("msg", "原密码不正确");
            return "admin/system/system";
        }
        backDao.changepwd(backUser.getUsername(), newpwd);
        backUser.setPwd(newpwd);
        session.setAttribute("myuser", backUser);
        model.addAttribute("msg", "修改成功");
        return "admin/system/system";
    }

    //进入商铺管理页面
    @Admin("/back/shopManage")
    @RequestMapping("shopManage")
    public String shopManage() {
        return "admin/shopManage/shopManage";
    }

    //ajax异步加载商铺数据
    @Admin("/back/shops")
    @RequestMapping("shops")
    @ResponseBody
    public PageData<Shop> shops(Shop shop) {
        PageData<Shop> pageData = new PageData<>();
        pageData.setData(backDao.findShopsByShop(shop));
        pageData.setTotal(backDao.countShop(shop));
        return pageData;
    }

    //审核通过
    @Admin("/back/checkpass")
    @RequestMapping("checkpass")
    public String checkpass(Integer id, String openid) {
        Shop shop = backDao.geShopByid(id);
        //设置openids表
        backDao.checkpass2(openid);
        //设置shopinfo表
        backDao.checkpass(id);
        BackUser backUser = new BackUser();
        backUser.setPwd(shop.getPwd());
        backUser.setUsername(shop.getPhoneNum());
        backUser.setOpenid(openid);
        backDao.addBackUser(backUser);//添加店主后台账户
        return "admin/shopManage/shopManage";
    }

    //审核失败
    @Admin("/back/checkdeny")
    @RequestMapping("checkdeny")
    public String checkdeny(Integer id, String openid) {
        //设置openids表
        backDao.checkdeny(id);
        //设置shopinfo表
        backDao.checkdeny2(openid);
        return "admin/shopManage/shopManage";
    }

    //超级管理员看到的订单列表
    @Admin("/back/orderList")
    @RequestMapping("orderList")
    public String orderList() {
        return "admin/orderList/orderList";
    }

    //ajax异步加载超级管理员订单数据
    @Admin("/back/adminorders")
    @RequestMapping("adminorders")
    @ResponseBody
    public PageData<BackOrder> adminorders(BackOrder order) {
        PageData<BackOrder> pageData = new PageData<>();
        pageData.setData(backDao.findShopsByBackOrder(order));
        pageData.setTotal(backDao.countBackOrder(order));
        return pageData;
    }

    @Admin("/back/fundManage")
    //超级管理员进行资金管理的页面
    @RequestMapping("fundManage")
    public String fundManage() {
        return "admin/fundManage/fundManage";
    }

    //超级管理员管理区域
    @Admin("/back/areaManage")
    @RequestMapping("areaManage")
    public String areaManage() {
        return "admin/areaManage/areaManage";
    }

    //加载区域代理数据
    @Admin("/back/areaagents")
    @RequestMapping("areaagents")
    @ResponseBody
    public PageData<BackOrder> areaagents(AreaAgent areaAgent) {
        PageData<BackOrder> pageData = new PageData<>();
        pageData.setData(backDao.findAreaAgentByAreaAgent(areaAgent));
        pageData.setTotal(backDao.countAreaAgent(areaAgent));
        return pageData;
    }
    //跳转到代理商添加页面
    @Admin("/back/toaddagent")
    @RequestMapping("toaddagent")
    public String toaddagent(Model model) {
        model.addAttribute("type", "添加代理商");
        return "admin/areaManage/agentadd";
    }
    @Admin("/back/addagent")
    @RequestMapping("addagent")
    public String addagent(AreaAgent areaAgent) {
        backDao.addAreaAgent(areaAgent);
        return "admin/areaManage/areaManage";
    }
        //代理商主页
    @Agent("/back/main")
    @RequestMapping("main")
    public String main() {
        return "agent/main";
    }

    @Agent("/back/agentShopManage")
    @RequestMapping("agentShopManage")
    public String agentShopManage() {
        return "agent/shopManage/shopManage";
    }

    @Agent("/back/agentOrderList")
    @RequestMapping("agentOrderList")
    public String agentOrderList() {
        return "agent/orderList/orderList";
    }

    @Agent("/back/agentUserManage")
    @RequestMapping("agentUserManage")
    public String agentUserManage() {
        return "agent/userManage/userManage";
    }

    @Agent("/back/agentfundManage")
    @RequestMapping("agentfundManage")
    public String agentfundManage() {
        return "agent/fundManage/fundManage";
    }

    @Agent("/back/agentpersonCenter")
    @RequestMapping("agentpersonCenter")
    public String agentpersonCenter() {
        return "agent/personCenter/personCenter";
    }

    //店主主页
    @Shopper("/back/shome")
    @RequestMapping("shome")
    public String shome() {
        return "shopper/shome";
    }

    //店主商品管理
    @Shopper("/back/goodsManage")
    @RequestMapping("goodsManage")
    public String goodsManage() {
        return "shopper/goodsManage/goodsManage";
    }

    //异步加载商品数据
    @Shopper("/back/goodses")
    @RequestMapping("goodses")
    @ResponseBody
    public PageData<Goods> goodses(HttpSession session, Goods goods) {
        goods.setShopid((Integer) session.getAttribute("shopid"));
        PageData<Goods> pageData = new PageData<>();
        pageData.setData(backDao.findGoodsByGoods(goods));
        pageData.setTotal(backDao.countGoods(goods));
        return pageData;
    }

    //跳转到商品添加页面
    @Shopper("/back/toaddgoods")
    @RequestMapping("toaddgoods")
    public String toaddgoods(Model model) {
        model.addAttribute("type", "添加商品");
        return "shopper/goodsManage/goodsadd";
    }

    @Shopper("/back/addgoods")
    @RequestMapping("addgoods")
    public String addgoods(HttpSession session, Goods goods, @RequestParam("file") MultipartFile[] file) {
        int shopid = (Integer) session.getAttribute("shopid");
        Shop s=backDao.getShopByid(shopid);
        goods.setShopid(shopid);
        goods.setLat(s.getLat());
        goods.setLng(s.getLng());
        for (int i = 0; i < file.length; ++i) {
            String filename = UUID.randomUUID().toString() + ".jpg";
            if (!file[i].isEmpty()) {
                try {
                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(this.filepath + filename));
                    out.write(file[i].getBytes());
                    out.flush();
                    out.close();
                } catch (Exception var9) {
                    var9.printStackTrace();
                }
                switch (i) {
                    case 0:
                        goods.setPic(filename);
                        break;
                    case 1:
                        goods.setContent(filename);
                }
            }
        }
        backDao.saveGood(goods);
        return "shopper/goodsManage/goodsManage";
    }

    //    //根据商铺id和商品类型获取商品参数
//    @Shopper("/back/getparams")
//    @RequestMapping("getparams")
//    @ResponseBody
//    public Map<String,List<String>> getparams(HttpSession session,String type,String subclass){
//        System.out.println(type);
//        System.out.println(subclass);
//        Integer shopid = (Integer) session.getAttribute("shopid");
//        GoodsParam goodsParam = backDao.getparams(shopid,type,subclass);
//        Map<String,List<String>> map = new HashMap<>();
//        if(null!=goodsParam){
//            if((null!=goodsParam.getOnevalue()) && (null!= goodsParam.getParamone())){
//                List<String> prms = Arrays.asList(goodsParam.getOnevalue().split(","));
//                map.put(goodsParam.getParamone(),prms);
//            }
//            if((null!=goodsParam.getParamtwo()) && (null!= goodsParam.getParamtwo())){
//                List<String> prms = Arrays.asList(goodsParam.getTwovalue().split(","));
//                map.put(goodsParam.getParamtwo(),prms);
//            }
//            if((null!=goodsParam.getThreevalue()) && (null!= goodsParam.getParamthree())){
//                List<String> prms = Arrays.asList(goodsParam.getThreevalue().split(","));
//                map.put(goodsParam.getParamthree(),prms);
//            }
//        }
//        return map;
//    }
//    //参数管理页面
//    @Shopper("/back/paramsmanage")
//    @RequestMapping("paramsmanage")
//    public String paramsmanage(){
//        return "shopper/paramsmanage/paramsmanage";
//    }
//    //参数管理页面数据
//    @Shopper("/back/paramslist")
//    @RequestMapping("paramslist")
//    @ResponseBody
//    public PageData<GoodsParam> paramslist(GoodsParam param,HttpSession session){
//        Integer shopid = (Integer) session.getAttribute("shopid");
//        param.setShopid(shopid);
//        PageData<GoodsParam> pageData = new PageData();
//        pageData.setData(backDao.findParamsByGoodsParam(param));
//        pageData.setTotal(backDao.countParams(param));
//        return pageData;
//    }
//    //跳转到参数添加页面
//    @Shopper("/back/toparamsadd")
//    @RequestMapping("toparamsadd")
//    public String toparamsadd(){
//        return "shopper/paramsmanage/paramsadd";
//    }
//    //添加参数
//    @Shopper("/back/addparams")
//    @RequestMapping("addparams")
//    public String addparams(GoodsParam goodsParam,HttpSession session){
//        Integer shopid = (Integer) session.getAttribute("shopid");
//        goodsParam.setShopid(shopid);
//        if(null==goodsParam.getParamone() || "".equals(goodsParam.getParamone().trim())){
//            goodsParam.setOnevalue(null);
//            goodsParam.setParamone(null);
//        }
//        if(null==goodsParam.getParamtwo() || "".equals(goodsParam.getParamtwo().trim())){
//            goodsParam.setTwovalue(null);
//            goodsParam.setParamtwo(null);
//        }
//        if(null==goodsParam.getParamthree() || "".equals(goodsParam.getParamthree().trim())){
//            goodsParam.setThreevalue(null);
//            goodsParam.setParamthree(null);
//        }
//        backDao.addGoodsParams(goodsParam);
//        return "shopper/paramsmanage/paramsmanage";
//    }
    //店主订单列表
    @Shopper("/back/shopOrderList")
    @RequestMapping("shopOrderList")
    public String shopOrderList() {
        return "shopper/orderList/orderList";
    }

    //店主用户管理
    @Shopper("/back/shopUserManage")
    @RequestMapping("shopUserManage")
    public String shopUserManage() {
        return "shopper/userManage/userManage";
    }

    //店主个人中心
    @Shopper("/back/shopPersonCenter")
    @RequestMapping("shopPersonCenter")
    public String shopPersonCenter(HttpSession session, Model model) {
        int shopid = (Integer) session.getAttribute("shopid");
        BackUser backUser = (BackUser) session.getAttribute("myuser");
        Shop shop = backDao.geShopByid(shopid);
        shop.setUname(backUser.getUsername());
        shop.setPwd(backUser.getPwd());
        System.out.println(shop);
        model.addAttribute("shop", shop);
        return "shopper/personCenter/personCenter";
    }

    @Agent("/back/logout")
    @Admin("/back/logout")
    @Shopper("/back/logout")
    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("myuser");
        return "login";
    }
}
