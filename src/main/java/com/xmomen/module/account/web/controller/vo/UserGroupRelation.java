package com.xmomen.module.account.web.controller.vo;

import java.io.Serializable;

/**
 * Created by Jeng on 2016/2/1.
 */
public class UserGroupRelation implements Serializable {

    private String username;
    private String email;
    private String userId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
