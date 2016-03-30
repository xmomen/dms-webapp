package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_manager_company")
public class CdManagerCompany extends BaseMybatisModel {
    /**
     * 
     */
    private Integer cdManagerCompanyId;

    /**
     * 单位
     */
    private Integer cdCompanyId;

    /**
     * 客户经理
     */
    private Integer cdManagerId;

    @Column(name = "CD_MANAGER_COMPANY_ID")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public Integer getCdManagerCompanyId() {
        return cdManagerCompanyId;
    }

    public void setCdManagerCompanyId(Integer cdManagerCompanyId) {
        this.cdManagerCompanyId = cdManagerCompanyId;
        if(cdManagerCompanyId == null){
              removeValidField("cdManagerCompanyId");
              return;
        }
        addValidField("cdManagerCompanyId");
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

    @Column(name = "CD_MANAGER_ID")
    public Integer getCdManagerId() {
        return cdManagerId;
    }

    public void setCdManagerId(Integer cdManagerId) {
        this.cdManagerId = cdManagerId;
        if(cdManagerId == null){
              removeValidField("cdManagerId");
              return;
        }
        addValidField("cdManagerId");
    }
}