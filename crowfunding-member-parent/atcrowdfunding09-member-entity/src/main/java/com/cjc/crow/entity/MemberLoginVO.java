package com.cjc.crow.entity;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: cjc
 * Date: 2020/11/7
 * Time: 17:08
 * To change this template use File | Settings | File Templates.
 *
 * 登录成功后，封装用户信息，保存到session中
 **/
public class MemberLoginVO implements Serializable {

    private Integer id;

    private String username;

    private String email;

    public MemberLoginVO(){

    }

    public MemberLoginVO(Integer id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "MemberLoginVO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
