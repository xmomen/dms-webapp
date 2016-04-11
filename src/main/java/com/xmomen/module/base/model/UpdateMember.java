package com.xmomen.module.base.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class UpdateMember implements Serializable{
	/**
     * 会员编号
     */
    private String memberCode;

    /**
     * 姓名
     */
    @NotNull
    @NotBlank
    private String name;

    /**
     * 手机号
     */
    @NotNull
    @NotBlank
    private String phoneNumber;
    
    private String spareName;
    
    private String spareName2;
    /**
     * 备用手机号1
     */
    private String spareTel;

    /**
     * 备用手机号2
     */
    private String spareTel2;

    /**
     * 家庭固话
     */
    private String telNumber;

    /**
     * 办公室电话
     */
    private String officeTel;

    /**
     * 收货地址
     */
    @NotNull
    @NotBlank
    private String address;

    /**
     * 备用地址1
     */
    private String spareAddress;

    /**
     * 备用地址2
     */
    private String spareAddress2;

    /**
     * 1-潜在客户，2-普通客户，3-优质客户
     */
    private Integer memberType;

    /**
     * 所属单位
     */
    private Integer cdCompanyId;

    /**
     * 所属客服经理
     */
    private Integer cdUserId;

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSpareTel() {
		return spareTel;
	}

	public void setSpareTel(String spareTel) {
		this.spareTel = spareTel;
	}

	public String getSpareTel2() {
		return spareTel2;
	}

	public void setSpareTel2(String spareTel2) {
		this.spareTel2 = spareTel2;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSpareAddress() {
		return spareAddress;
	}

	public void setSpareAddress(String spareAddress) {
		this.spareAddress = spareAddress;
	}

	public String getSpareAddress2() {
		return spareAddress2;
	}

	public void setSpareAddress2(String spareAddress2) {
		this.spareAddress2 = spareAddress2;
	}

	public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

	public Integer getCdCompanyId() {
		return cdCompanyId;
	}

	public void setCdCompanyId(Integer cdCompanyId) {
		this.cdCompanyId = cdCompanyId;
	}

	public Integer getCdUserId() {
		return cdUserId;
	}

	public void setCdUserId(Integer cdUserId) {
		this.cdUserId = cdUserId;
	}

	public String getSpareName() {
		return spareName;
	}

	public void setSpareName(String spareName) {
		this.spareName = spareName;
	}

	public String getSpareName2() {
		return spareName2;
	}

	public void setSpareName2(String spareName2) {
		this.spareName2 = spareName2;
	}
}
