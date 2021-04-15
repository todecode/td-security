package com.td.security.core.validate.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @className: ImageCode
 * @description:
 * @author: cyd
 * @date: 2021/4/15 下午3:07
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageCode {

    private BufferedImage image;

    private String code;

    private LocalDateTime expreTime;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        this.expreTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expreTime);
    }
}
