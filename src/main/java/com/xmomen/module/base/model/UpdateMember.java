package com.xmomen.module.base.model;

import com.xmomen.module.member.model.MemberAddressCreate;
import com.xmomen.module.member.model.MemberAddressUpdate;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class UpdateMember implements Serializable {
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

    //地址集合
    List<MemberAddressUpdate> memberAddressList;

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

    public List<MemberAddressUpdate> getMemberAddressList() {
        return memberAddressList;
    }

    public void setMemberAddressList(List<MemberAddressUpdate> memberAddressList) {
        this.memberAddressList = memberAddressList;
    }
}
