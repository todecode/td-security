package com.td.security.browser;

import com.td.security.browser.authentication.TdAuthenticationFailureHandler;
import com.td.security.browser.authentication.TdAuthenticationSuccessHandler;
import com.td.security.core.authentication.AbstractChannelSecurityConfig;
import com.td.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.td.security.core.properties.SecurityConstants;
import com.td.security.core.properties.SecurityProperties;
import com.td.security.core.validate.code.ValidateCodeSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


/**
 * @className: BrowserSecurityConfig
 * @description: 浏览器安全配置
 * @author: cyd
 * @date: 2019/12/7 下午4:42
 **/
@Slf4j
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private TdAuthenticationSuccessHandler tdAuthenticationSuccessHandler;

    @Autowired
    private TdAuthenticationFailureHandler tdAuthenticationFailureHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    /**
     *
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

        applyPasswordAuthenticationConfig(http);

        http.apply(validateCodeSecurityConfig) // 验证码相关配置
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)// 短信验证码相关配置
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();

    }
}
