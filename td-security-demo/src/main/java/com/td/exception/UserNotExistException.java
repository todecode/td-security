package com.td.exception;

import lombok.Data;

/**
 * @className: UserNotExistException
 * @description:
 * @author: cyd
 * @date: 2021/3/12 上午11:45
 **/
@Data
public class UserNotExistException  extends RuntimeException{

    /**  */
    private String id;
    public UserNotExistException(String id){
        super("user not exist!");
        this.id= id;
    }


}
