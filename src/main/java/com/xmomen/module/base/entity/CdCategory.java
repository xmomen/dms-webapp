package com.xmomen.module.base.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_category")
public class CdCategory extends BaseMybatisModel {
    /**
     * 
     */
    private Integer cdCategoryId;

    /**
     * 类别名称
     */
    private String categoryName;

    @Column(name = "CD_CATEGORY_ID")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public Integer getCdCategoryId() {
        return cdCategoryId;
    }

    public void setCdCategoryId(Integer cdCategoryId) {
        this.cdCategoryId = cdCategoryId;
        if(cdCategoryId == null){
              removeValidField("cdCategoryId");
              return;
        }
        addValidField("cdCategoryId");
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