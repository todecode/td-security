package com.td.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @className: ValidateCodeGenerator
 * @description: 验证码生成接口
 * @author: cyd
 * @date: 2021/4/16 上午12:31
 **/
public interface ValidateCodeGenerator {

    /**
     * 验证码生成接口
     * @param request
     * @return
     */
    ImageCode generate(ServletWebRequest request);
}
