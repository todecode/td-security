package com.td.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @className: TimeInterceptor
 * @description:
 * @author: cyd
 * @date: 2021/3/13 上午11:57
 **/
@Component
public class TimeInterceptor implements HandlerInterceptor {

    /**
     * 方法调用之前
     * @param request
     * @param response
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println("preHandle");
        System.out.println(((HandlerMethod)o).getBean().getClass().getName());
        System.out.println(((HandlerMethod)o).getMethod().getName() );
        request.setAttribute("startTime",System.currentTimeMillis());
        return true;
    }

    /**
     * 控制器处理之后，若控制器抛异常，这方法则不会被调用
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        long startTime = (long) httpServletRequest.getAttribute("startTime");
        System.out.println("time interceptor 耗时： " +(System.currentTimeMillis() - startTime));
    }

    /**
     * 方法之后都会被调用。不管是异常情况.
     * 注意。拦截器会拦截所有过滤器， 同时也拦截BasicErrorController
     * 若异常被处理。则返回异常为空
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion");
        long startTime = (long) httpServletRequest.getAttribute("startTime");
        System.out.println("time interceptor 耗时： " +(System.currentTimeMillis() - startTime));
        System.out.println("ex is "+e);
    }
}
