package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_coupon_category")
public class CdCouponCategory extends BaseMybatisModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 类别类型(1-卡，2-劵)
     */
    private Integer categoryType;

    /**
     * 类别名称
     */
    private String categoryName;

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

    @Column(name = "CATEGORY_TYPE")
    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
        if(categoryType == null){
              removeValidField("categoryType");
              return;
        }
        addValidField("categoryType");
    }

    @Column(name = "CATEGORY_NAME")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        if(categoryName == null){
              removeValidField("categoryName");
              return;
        }
        addValidField("categoryName");
    }
}