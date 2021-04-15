package com.td.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @className: ValidateCodeException
 * @description: 图形验证码异常
 * @author: cyd
 * @date: 2021/4/15 下午3:39
 **/
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg){
        super(msg);
    }
}
