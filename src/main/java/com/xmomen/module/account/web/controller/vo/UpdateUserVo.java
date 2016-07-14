package com.xmomen.module.account.web.controller.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Jeng on 2016/1/7.
 */
public @Data class UpdateUserVo implements Serializable {

    @NotNull
    private Integer id;
    @NotBlank
    private String username;
    private String email;
    @NotBlank
    private String realName;
    @NotNull
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
