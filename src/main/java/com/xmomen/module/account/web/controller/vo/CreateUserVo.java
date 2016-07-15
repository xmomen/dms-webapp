package com.xmomen.module.account.web.controller.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Jeng on 2016/1/7.
 */
public @Data class CreateUserVo implements Serializable {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String email;
    @NotBlank
    private String realName;
    @NotBlank
    private String phoneNumber;
    private Integer age;
    private String qq;
    private String officeTel;
    private Boolean locked;
    /**
     * 1-男，2女
     */
    private Integer sex;
    
    private Integer organizationId;
    
    private List<Integer> userGroupIds;
    
}
