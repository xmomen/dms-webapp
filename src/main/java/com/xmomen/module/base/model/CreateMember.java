package com.xmomen.module.base.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.xmomen.module.member.model.MemberAddressCreate;
import com.xmomen.module.member.model.MemberAddressModel;
import org.hibernate.validator.constraints.NotBlank;

public class CreateMember implements Serializable {
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

    /**
     * 卡号
     */
    private String couponNumber;


    /**
     * 家庭固话
     */
    private String telNumber;

    /**
     * 办公室电话
     */
    private String officeTel;

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
    
    private String password;
    
    private String email;

    //地址集合
    List<MemberAddressCreate> memberAddressList;

    public List<MemberAddressCreate> getMemberAddressList() {
        return memberAddressList;
    }

    public void setMemberAddressList(List<MemberAddressCreate> memberAddressList) {
        this.memberAddressList = memberAddressList;
    }

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

    public String getCouponNumber() {
        return couponNumber;
    }

    public void setCouponNumber(String couponNumber) {
        this.couponNumber = couponNumber;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
    	return email; 
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
}
