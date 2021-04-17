package com.td.security.core.validate.code.sms;

/**
 * @className: SmsCodeSender
 * @description: 短信验证码发送接口，通过实现接口，从而达到覆盖配置
 * @author: cyd
 * @date: 2021/4/16 上午11:13
 **/
public interface SmsCodeSender {

    void send(String mobile,String code);
}
