package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_member")
public class CdMember extends BaseMybatisModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 所属单位
     */
    private Integer cdCompanyId;

    /**
     * 所属客服经理
     */
    private Integer cdUserId;

    /**
     * 备用收货人
     */
    private String spareName;

    /**
     * 备用收货人2
     */
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

    @Column(name = "ID")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        if(id == null){
              removeValidField("id");
              return;
        }
        addValidField("id");
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if(name == null){
              removeValidField("name");
              return;
        }
        addValidField("name");
    }

    @Column(name = "PHONE_NUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        if(phoneNumber == null){
              removeValidField("phoneNumber");
              return;
        }
        addValidField("phoneNumber");
    }

    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        if(address == null){
              removeValidField("address");
              return;
        }
        addValidField("address");
    }

    @Column(name = "CD_COMPANY_ID")
    public Integer getCdCompanyId() {
        return cdCompanyId;
    }

    public void setCdCompanyId(Integer cdCompanyId) {
        this.cdCompanyId = cdCompanyId;
        if(cdCompanyId == null){
              removeValidField("cdCompanyId");
              return;
        }
        addValidField("cdCompanyId");
    }

    @Column(name = "CD_USER_ID")
    public Integer getCdUserId() {
        return cdUserId;
    }

    public void setCdUserId(Integer cdUserId) {
        this.cdUserId = cdUserId;
        if(cdUserId == null){
              removeValidField("cdUserId");
              return;
        }
        addValidField("cdUserId");
    }

    @Column(name = "SPARE_NAME")
    public String getSpareName() {
        return spareName;
    }

    public void setSpareName(String spareName) {
        this.spareName = spareName;
        if(spareName == null){
              removeValidField("spareName");
              return;
        }
        addValidField("spareName");
    }

    @Column(name = "SPARE_NAME2")
    public String getSpareName2() {
        return spareName2;
    }

    public void setSpareName2(String spareName2) {
        this.spareName2 = spareName2;
        if(spareName2 == null){
              removeValidField("spareName2");
              return;
        }
        addValidField("spareName2");
    }

    @Column(name = "SPARE_TEL")
    public String getSpareTel() {
        return spareTel;
    }

    public void setSpareTel(String spareTel) {
        this.spareTel = spareTel;
        if(spareTel == null){
              removeValidField("spareTel");
              return;
        }
        addValidField("spareTel");
    }

    @Column(name = "SPARE_TEL2")
    public String getSpareTel2() {
        return spareTel2;
    }

    public void setSpareTel2(String spareTel2) {
        this.spareTel2 = spareTel2;
        if(spareTel2 == null){
              removeValidField("spareTel2");
              return;
        }
        addValidField("spareTel2");
    }

    @Column(name = "TEL_NUMBER")
    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
        if(telNumber == null){
              removeValidField("telNumber");
              return;
        }
        addValidField("telNumber");
    }

    @Column(name = "OFFICE_TEL")
    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
        if(officeTel == null){
              removeValidField("officeTel");
              return;
        }
        addValidField("officeTel");
    }

    @Column(name = "SPARE_ADDRESS")
    public String getSpareAddress() {
        return spareAddress;
    }

    public void setSpareAddress(String spareAddress) {
        this.spareAddress = spareAddress;
        if(spareAddress == null){
              removeValidField("spareAddress");
              return;
        }
        addValidField("spareAddress");
    }

    @Column(name = "SPARE_ADDRESS2")
    public String getSpareAddress2() {
        return spareAddress2;
    }

    public void setSpareAddress2(String spareAddress2) {
        this.spareAddress2 = spareAddress2;
        if(spareAddress2 == null){
              removeValidField("spareAddress2");
              return;
        }
        addValidField("spareAddress2");
    }

    @Column(name = "MEMBER_TYPE")
    public Integer getMemberType() {
        return memberType;
    }

    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
        if(memberType == null){
              removeValidField("memberType");
              return;
        }
        addValidField("memberType");
    }
}