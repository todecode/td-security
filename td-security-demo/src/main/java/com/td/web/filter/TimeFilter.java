package com.td.web.filter;


import javax.servlet.*;
import java.io.IOException;

/**
 * @className: TimeFilter
 * @description: 过滤器不能获取到方法里面的具体参数
 * @author: cyd
 * @date: 2021/3/13 上午11:41
 **/
//@Component 修改通过webconfig方式配置
public class TimeFilter  implements Filter {

    /**
     * 初始化
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");

    }

    /**
     * 处理过滤器逻辑
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("time filter start");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("time filter 耗时" + (System.currentTimeMillis() - start));
        System.out.println("time filter finish");
    }

    /**
     * 销毁
     */
    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}
