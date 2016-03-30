package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_company")
public class CdCompany extends BaseMybatisModel {
    /**
     * 
     */
    private Integer cdCompanyId;

    /**
     * 单位编号
     */
    private String companyCode;

    /**
     * 单位名称
     */
    private String companyName;

    /**
     * 单位地址
     */
    private String companyAddress;

    /**
     * 单位联系人
     */
    private String companyLeader;

    /**
     * 联系人电话
     */
    private String companyLeaderTel;

    @Column(name = "CD_COMPANY_ID")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
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

    @Column(name = "COMPANY_CODE")
    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
        if(companyCode == null){
              removeValidField("companyCode");
              return;
        }
        addValidField("companyCode");
    }

    @Column(name = "COMPANY_NAME")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
        if(companyName == null){
              removeValidField("companyName");
              return;
        }
        addValidField("companyName");
    }

    @Column(name = "COMPANY_ADDRESS")
    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
        if(companyAddress == null){
              removeValidField("companyAddress");
              return;
        }
        addValidField("companyAddress");
    }

    @Column(name = "COMPANY_LEADER")
    public String getCompanyLeader() {
        return companyLeader;
    }

    public void setCompanyLeader(String companyLeader) {
        this.companyLeader = companyLeader;
        if(companyLeader == null){
              removeValidField("companyLeader");
              return;
        }
        addValidField("companyLeader");
    }

    @Column(name = "COMPANY_LEADER_TEL")
    public String getCompanyLeaderTel() {
        return companyLeaderTel;
    }

    public void setCompanyLeaderTel(String companyLeaderTel) {
        this.companyLeaderTel = companyLeaderTel;
        if(companyLeaderTel == null){
              removeValidField("companyLeaderTel");
              return;
        }
        addValidField("companyLeaderTel");
    }
}