package com.shop.nearby.nearbyshop.inceptor;

import com.shop.nearby.nearbyshop.model.BackUser;
import com.shop.nearby.nearbyshop.model.Power;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BackSessionInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        ServletContext app = request.getServletContext();
        if (request.getRequestURI().equals("/back")||request.getRequestURI().equals("/back/backlogin")){
            return true;
        }
        o=request.getSession().getAttribute("myuser");
        if (o==null){
            response.sendRedirect("/back");
            return false;
        }
        //判断当前的角色
        BackUser user = (BackUser)o;
        int power = user.getPower();
        //方法里面的变量是线程独有的不会存在线程安全问题
        List<String> powerList;
        //声明一个列表存当前登录用户的权限。
        if(power==1){
            powerList=(List)app.getAttribute("admins");
        }else if(power==2){
            //获取所有代理商注解，将权限赋值给powerlist
            powerList=(List)app.getAttribute("agents");
        }else{
            //获取所有店铺主注解，将权限赋值给powerlist
            powerList=(List)app.getAttribute("shoppers");
        }
        System.out.println(powerList);
        System.out.println(request.getRequestURI());
        if(!powerList.contains(request.getRequestURI())){
            response.setCharacterEncoding("utf-8");
            response.sendError(403);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
