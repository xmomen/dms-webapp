package com.xmomen.module.account.web.controller.vo;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jeng on 2016/2/1.
 */
public class UpdateUserGroup implements Serializable {

    @Length(min = 0, max = 100)
    private String description;
    private List<Integer> userIdList;
    private Boolean available;

    public List<Integer> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<Integer> userIdList) {
        this.userIdList = userIdList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
