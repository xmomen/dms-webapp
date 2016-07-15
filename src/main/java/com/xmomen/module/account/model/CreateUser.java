package com.xmomen.module.account.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public @Data class CreateUser implements Serializable {
	
    private String username;
    private String email;
    private String password;
    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 年龄
     */
    private Integer age;
    /**
     * 1-男，2女
     */
    private Integer sex;

    /**
     * QQ
     */
    private String qq;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 办公室电话
     */
    private String officeTel;

    private Boolean locked = Boolean.FALSE;
    
    private Integer organizationId;
    
    private List<Integer> userGroupIds;
}
