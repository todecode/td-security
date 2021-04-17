package com.td.code;

import com.td.security.core.validate.code.ImageIO.ImageCode;
import com.td.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @className: DemoImageCodeGenerator
 * @description: 使用bean：imageCodeGenerator来覆盖原来图形验证码的配置
 * @author: cyd
 * @date: 2021/4/16 上午12:42
 **/
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证码生成代码");
        return null;
    }
}
