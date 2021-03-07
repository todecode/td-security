package com.td.controller;

import com.td.dto.User;
import com.td.dto.UserQueryCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: UserController
 * @description:
 * @author: cyd
 * @date: 2021/3/7 下午11:13
 **/
@RestController
public class UserController {

    /**
     *
     * @param condition
     * @param pageable 也可以使用PageableDefault设置默认的页数和页码
     * @return
     */
    @GetMapping("/user")
    public List<User> query(UserQueryCondition condition, Pageable pageable){
        // 将输入的指打印出来
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));

        System.out.println("size:"+pageable.getPageSize());
        System.out.println("sort"+pageable.getSort());
        System.out.println("pageNumber" + pageable.getPageNumber());

        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        return list;
    }
}
