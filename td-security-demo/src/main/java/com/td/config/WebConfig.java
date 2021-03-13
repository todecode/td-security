package com.td.config;

import com.td.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @className: WebConfig
 * @description:
 * @author: cyd
 * @date: 2021/3/13 上午11:45
 **/
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    TimeInterceptor timeInterceptor;

    /**
     * 拦截器注册器，
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }

    /**
     * 过滤器注册器
     * @return
     */
//    @Bean
//    public FilterRegistrationBean timeFilter(){
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        TimeFilter timeFilter = new TimeFilter();
//        filterRegistrationBean.setFilter(timeFilter);
//
//        // 相比之下。可以配置过滤的地址
//        List<String> url = new ArrayList<>();
//        url.add("/*");
//        filterRegistrationBean.setUrlPatterns(url);
//        return filterRegistrationBean;
//    }
}
