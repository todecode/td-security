package com.td.security.core.properties;

import lombok.Data;

/**
 * @className: ImageCodeProperties
 * @description: 图形验证码配置
 * @author: cyd
 * @date: 2021/4/16 上午12:07
 **/
@Data
public class SmsCodeProperties {
    private int length = 6;
    private int expireIn = 60;

    private String url;
}
