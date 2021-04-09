package com.td.security.browser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @className: BrowserSecurityConfig
 * @description: 浏览器安全配置
 * @author: cyd
 * @date: 2019/12/7 下午4:42
 **/
@Slf4j
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter  {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * formLogin() --> 表单登陆
     * httpBasic() --> 最初弹框登陆
     * authorizeRequests() -->对请求做一个授权
     * anyRequest() --> 任何请求
     * authenticated() --> 都需要身份证认证
     * antMatchers（） --> 访问这个url permitAll 不需要身份认证
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // security默认配置的代码

        http.formLogin()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();

    }
}
