package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_coupon_category_ref")
public class CdCouponCategoryRef extends BaseMybatisModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 卡劵类别
     */
    private Integer cdCouponCategoryId;

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
     * 份数
     */
    private Integer refCount;

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

    @Column(name = "CD_COUPON_CATEGORY_ID")
    public Integer getCdCouponCategoryId() {
        return cdCouponCategoryId;
    }

    public void setCdCouponCategoryId(Integer cdCouponCategoryId) {
        this.cdCouponCategoryId = cdCouponCategoryId;
        if(cdCouponCategoryId == null){
              removeValidField("cdCouponCategoryId");
              return;
        }
        addValidField("cdCouponCategoryId");
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