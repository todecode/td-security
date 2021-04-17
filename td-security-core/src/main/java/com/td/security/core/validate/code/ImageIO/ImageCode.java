package com.td.security.core.validate.code.ImageIO;

import com.td.security.core.validate.code.ValidateCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;

/**
 * @className: ImageCode
 * @description:
 * @author: cyd
 * @date: 2021/4/15 下午3:07
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageCode extends ValidateCode {

    private BufferedImage image;


    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code,expireIn);
        this.image = image;
    }
}
