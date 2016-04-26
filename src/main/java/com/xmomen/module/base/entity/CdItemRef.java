package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_item_ref")
public class CdItemRef extends BaseMybatisModel {
    /**
     * 
     */
    private Integer cdItemRefId;

    /**
     * 拓展类型
     */
    private String refType;

    /**
     * 拓展描述
     */
    private String refName;

    /**
     * 拓展值
     */
    private String refValue;

    /**
     * 对应产品
     */
    private Integer cdItemId;

    /**
     * 份数
     */
    private Integer refCount;

    @Column(name = "CD_ITEM_REF_ID")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public Integer getCdItemRefId() {
        return cdItemRefId;
    }

    public void setCdItemRefId(Integer cdItemRefId) {
        this.cdItemRefId = cdItemRefId;
        if(cdItemRefId == null){
              removeValidField("cdItemRefId");
              return;
        }
        addValidField("cdItemRefId");
    }

    @Column(name = "REF_TYPE")
    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
        if(refType == null){
              removeValidField("refType");
              return;
        }
        addValidField("refType");
    }

    @Column(name = "REF_NAME")
    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName;
        if(refName == null){
              removeValidField("refName");
              return;
        }
        addValidField("refName");
    }

    @Column(name = "REF_VALUE")
    public String getRefValue() {
        return refValue;
    }

    public void setRefValue(String refValue) {
        this.refValue = refValue;
        if(refValue == null){
              removeValidField("refValue");
              return;
        }
        addValidField("refValue");
    }

    @Column(name = "CD_ITEM_ID")
    public Integer getCdItemId() {
        return cdItemId;
    }

    public void setCdItemId(Integer cdItemId) {
        this.cdItemId = cdItemId;
        if(cdItemId == null){
              removeValidField("cdItemId");
              return;
        }
        addValidField("cdItemId");
    }

    @Column(name = "REF_COUNT")
    public Integer getRefCount() {
        return refCount;
    }

    public void setRefCount(Integer refCount) {
        this.refCount = refCount;
        if(refCount == null){
              removeValidField("refCount");
              return;
        }
        addValidField("refCount");
    }
}