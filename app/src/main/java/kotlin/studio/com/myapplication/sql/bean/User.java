package kotlin.studio.com.myapplication.sql.bean;

import kotlin.studio.com.myapplication.sql.DbField;
import kotlin.studio.com.myapplication.sql.DbTable;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : Android
 * Author     : 关羽
 * Date       : 2018-07-18 15:55
 */
@DbTable("tb_user")
public class User {

    public User() {
    }

    public User(String phoneNumber, String password, Boolean isLogin, String token, Long loginTime, Long outLoginTime, Long createTime) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.isLogin = isLogin;
        this.token = token;
        this.loginTime = loginTime;
        this.outLoginTime = outLoginTime;
        this.createTime = createTime;
    }

    @DbField("phoneNumber")
    public String phoneNumber;

    @DbField("password")
    public String password;

    @DbField("isLogin")
    public Boolean isLogin;

    @DbField("token")
    public String token;

    @DbField("loginTime")
    public Long loginTime;

    @DbField("outLoginTime")
    public Long outLoginTime;

    @DbField("createTime")
    public Long createTime;


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLogin() {
        return isLogin;
    }

    public void setLogin(Boolean login) {
        isLogin = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getOutLoginTime() {
        return outLoginTime;
    }

    public void setOutLoginTime(Long outLoginTime) {
        this.outLoginTime = outLoginTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
