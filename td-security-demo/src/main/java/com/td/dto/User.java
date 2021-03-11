package com.td.dto;

import com.fasterxml.jackson.annotation.JsonView;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @className: User
 * @description:
 * @author: cyd
 * @date: 2021/3/7 下午11:12
 **/
public class User {

    public interface UserSimpleView{}
    public interface UserDetailView extends UserSimpleView {};
    /**  */
    private String id;

    /**  */
    private String userName;

    /**  */
    @NotNull(message = "密码不能为空")
    private String password;

    /**  */
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
