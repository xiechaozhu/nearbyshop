package com.shop.nearby.nearbyshop.inceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String session_key = request.getParameter("sessionKey");
        if(null==session_key){
            //什么也不做
            response.sendError(400,"非法请求");
            return false;
        }
        //这里需要客户端和服务端进行会话一致性处理：第一次交互服务端发送sessionId到客户端
        //客户端保存。以后会话需要传sessionId，这样才能从session中取到值。
        o=request.getSession().getAttribute(request.getParameter("openid"));
        if (!o.equals(session_key)){
            //什么也不做
            response.sendError(400,"非法请求");
            return false;
        }
        //允许通过
        return  true;
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception { }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception { }
}
