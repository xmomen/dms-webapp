package com.xmomen.module.account.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * Created by Jeng on 2016/1/28.
 */
public @Data class User implements Serializable {
    private Integer id;
    private String username;
    private String realName;
    private String phoneNumber;
    private String sex;
    private Integer age;
    private String qq;
    private String officeTel;
    private Integer locked;
    private String email;
    private String organization;//组织
    private Integer organizationId;
    private List<UserGroup> userGroups;
}
