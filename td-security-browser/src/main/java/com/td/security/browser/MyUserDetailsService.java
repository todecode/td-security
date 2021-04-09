package com.td.security.browser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @className: MyUserDetailService
 * @description:
 * @author: cyd
 * @date: 2019/12/12 上午12:38
 **/
@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("登陆的用户名"+s);
        // 根据用户名查找用户信息
        // commaSeparatedStringToAuthorityList把逗号隔开的对象返回所需要的authorities对象

        String password = passwordEncoder.encode("123456");
        log.info("数据库密码："+password);

        // 根据查找的用户判断用户是否被冻结
        // enabled（账号是否可用）
        // accountNonExpired（账户没有过期）
        // credentialsNonExpired（密码是否过期）
        // accountNonLocked（账号是否被锁定）
        // passwordEncoder.encode应该在用户注册流程中使用。 这里为了方便演示
//        return new User(s,passwordEncoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return new User(s,password,true,true,true,true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
