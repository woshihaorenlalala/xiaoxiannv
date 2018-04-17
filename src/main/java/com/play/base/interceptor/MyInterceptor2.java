package com.play.base.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/2/23.
 */
public class MyInterceptor2 implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println(">>>>>MyInterceptor2>>>>>>>>>>>处理请求方法之前调用（controller方法调用之前）");
        return true; //只有为true才会继续往下走，false会取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println(">>>>>MyInterceptor2>>>>>>>>>>>请求处理之后调用，但是在视图渲染之前（controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println(">>>>>MyInterceptor2>>>>>>>>>>>整个请求处理之后调用，也就是在DispatcherServlet渲染了对应的视图之后执行（主要用于资源清理功能）");
    }
}
