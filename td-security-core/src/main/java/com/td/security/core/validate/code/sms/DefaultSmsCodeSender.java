package com.td.security.core.validate.code.sms;

/**
 * @className: DevaultSmsCodeSender
 * @description: 默认手机验证码发送流程，若有实现，则会覆盖该实现
 * @author: cyd
 * @date: 2021/4/16 上午11:14
 **/
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机"+mobile+"发送短信验证码"+code);
    }
}
