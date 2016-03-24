package com.xmomen.module.account.model;

import java.io.Serializable;

/**
 * Created by Jeng on 2016/1/28.
 */
public class User implements Serializable {
    private Integer userId;
    private String username;
    private Integer locked;
    private String email;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
