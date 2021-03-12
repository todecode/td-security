package com.td.service.impl;

import com.td.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @className: HelloServiceImpl
 * @description:
 * @author: cyd
 * @date: 2021/3/12 上午10:35
 **/
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String greeting(String name) {
        System.out.println(name);
        return "hello "+name;
    }
}
