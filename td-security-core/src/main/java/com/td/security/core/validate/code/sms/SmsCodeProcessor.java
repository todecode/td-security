package com.td.security.core.validate.code.sms;

import com.td.security.core.validate.code.ValidateCode;
import com.td.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @className: SmsCodeProcessor
 * @description: 短信验证码处理器
 * @author: cyd
 * @date: 2021/4/16 上午11:38
 **/
@Component("smsCodeProcessor")
public class SmsCodeProcessor  extends AbstractValidateCodeProcessor<ValidateCode> {

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(),"mobile");
        smsCodeSender.send(mobile,validateCode.getCode());
    }
}
