package com.td.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className: UserQueryCondition
 * @description:
 * @author: cyd
 * @date: 2021/3/7 下午11:28
 **/
@Data
public class UserQueryCondition {
    /**  */
    @ApiModelProperty("用户名")
    private String userName;
    /**  */
    @ApiModelProperty("年龄")
    private String age;
    /**  */
    private String ageTo;
    /**  */
    private String xxx;
}
