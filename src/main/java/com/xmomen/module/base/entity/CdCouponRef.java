package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_coupon_ref")
public class CdCouponRef extends BaseMybatisModel {
    /**
     * 
     */
    private Integer 主键id;

    /**
     * 卡劵
     */
    private Integer cdCouponId;

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

    @Column(name = "主键ID")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public Integer get主键id() {
        return 主键id;
    }

    public void set主键id(Integer 主键id) {
        this.主键id = 主键id;
        if(主键id == null){
              removeValidField("主键id");
              return;
        }
        addValidField("主键id");
    }

    @Column(name = "CD_COUPON_ID")
    public Integer getCdCouponId() {
        return cdCouponId;
    }

    public void setCdCouponId(Integer cdCouponId) {
        this.cdCouponId = cdCouponId;
        if(cdCouponId == null){
              removeValidField("cdCouponId");
              return;
        }
        addValidField("cdCouponId");
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
}