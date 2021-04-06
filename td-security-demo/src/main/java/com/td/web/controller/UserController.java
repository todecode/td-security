package com.td.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.td.dto.User;
import com.td.dto.UserQueryCondition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @className: UserController
 * @description:
 * @author: cyd
 * @date: 2021/3/7 下午11:13
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");
        return user;
    }

    /**
     *
     * @param condition
     * @param pageable 也可以使用PageableDefault设置默认的页数和页码
     * @return
     */
    @GetMapping()
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "查询用户列表")
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

    @GetMapping(value = "/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@ApiParam(value = "用户id") @PathVariable String id){

        // spring boot默认异常处理
//        throw new RuntimeException("user not Exist");

        // 自定义异常处理
//        throw new UserNotExistException(id);
        System.out.println("进入getInfo方法。。。。");
        User user = new User();
        user.setUserName("tom");
        user.setBirthday(new Date());
        return user;
    }

    @PutMapping(value = "/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> {
//                FieldError fieldError = (FieldError) error;
//                String message = fieldError.getField() +" " + error.getDefaultMessage();
                System.out.println(error.getDefaultMessage());
            });
        }

        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");
        return user;
    }

    @DeleteMapping(value = "/{id:\\d+}")
    public void delete(@PathVariable String id){
        System.out.println(id);
    }
}
