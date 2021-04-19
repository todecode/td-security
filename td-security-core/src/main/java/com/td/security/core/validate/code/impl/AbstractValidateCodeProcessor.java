package com.td.security.core.validate.code.impl;

import com.td.security.core.validate.code.ValidateCode;
import com.td.security.core.validate.code.ValidateCodeGenerator;
import com.td.security.core.validate.code.ValidateCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @className: AbstractValidateCodeProcessor
 * @description:
 * @author: cyd
 * @date: 2021/4/16 下午2:27
 **/
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 收集系统中所有{@link ValidateCodeGenerator} 接口的实现。
     */
    @Autowired
    private Map<String,ValidateCodeGenerator> validateCodeGenerators;


    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request,validateCode);
        send(request,validateCode);
    }

    private C generate(ServletWebRequest request){
        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type + "CodeGenerator");
        return (C) validateCodeGenerator.generate(request);
    }

    private void save(ServletWebRequest request,C validateCode){
        sessionStrategy.setAttribute(request,ValidateCodeProcessor.SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(),validateCode);
    }

    /**
     * 发送验证码，由子类实现
     * @param request
     * @param validateCode
     * @throws Exception
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    /**
     * 更具请求的url获取验证码的类型
     * @param request
     * @return
     */
    private String getProcessorType(ServletWebRequest request){
        return StringUtils.substringAfter(request.getRequest().getRequestURI(),"/code/");
    }
}
