package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_plan_item")
public class CdPlanItem extends BaseMybatisModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 计划ID
     */
    private Integer cdPlanId;

    /**
     * 搭配的产品
     */
    private Integer cdItemId;

    /**
     * 份数
     */
    private Integer countValue;

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

    @Column(name = "CD_PLAN_ID")
    public Integer getCdPlanId() {
        return cdPlanId;
    }

    public void setCdPlanId(Integer cdPlanId) {
        this.cdPlanId = cdPlanId;
        if(cdPlanId == null){
              removeValidField("cdPlanId");
              return;
        }
        addValidField("cdPlanId");
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

    @Column(name = "COUNT_VALUE")
    public Integer getCountValue() {
        return countValue;
    }

    public void setCountValue(Integer countValue) {
        this.countValue = countValue;
        if(countValue == null){
              removeValidField("countValue");
              return;
        }
        addValidField("countValue");
    }
}