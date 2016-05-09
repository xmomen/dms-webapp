package com.xmomen.module.account.model;

import java.io.Serializable;

/**
 * Created by Jeng on 2016/1/28.
 */
public class User implements Serializable {
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
    private String userGorup;//用户组
    private Integer organizationId;
    private Integer userGorupId;
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

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getUserGorup() {
		return userGorup;
	}

	public void setUserGorup(String userGorup) {
		this.userGorup = userGorup;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getUserGorupId() {
		return userGorupId;
	}

	public void setUserGorupId(Integer userGorupId) {
		this.userGorupId = userGorupId;
	}
}
