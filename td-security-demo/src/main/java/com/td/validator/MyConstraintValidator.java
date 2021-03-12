package com.td.validator;

import com.td.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @className: MyConstraintValidator
 * @description: 注解验证器，使用spring容器里面的bean，无需要在头上标记Component
 * @author: cyd
 * @date: 2021/3/12 上午10:32
 **/
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {

    @Autowired
    HelloService helloService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        helloService.greeting("Mr.tom");
        System.out.println(value);
        return false;
    }
}
