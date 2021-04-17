package com.td.security.core.validate.code;

import com.td.security.core.properties.SecurityProperties;
import com.td.security.core.validate.code.ImageIO.ImageCodeGenerator;
import com.td.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.td.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: ValidateCodeBeanConfig
 * @description:
 * @author: cyd
 * @date: 2021/4/16 上午12:37
 **/
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * ConditionalOnMissingBea：注解意思：若没有这个imageCodeGeneratorbean，则用下面系统配置的
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name="imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    /**
     * 可通过设置name属性来覆盖，ConditionalOnMissingBean(name="smsCodeGenerator")
     * 也可以设置class的累实现做覆盖，ConditionalOnMissingBean(SmsCodeSender.class)
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        return new DefaultSmsCodeSender();
    }
}
