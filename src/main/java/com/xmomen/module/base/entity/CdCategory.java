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
    private Integer id;

    /**
     * 类别名称
     */
    private String categoryName;

    /**
     * 上级类别
     */
    private Integer parentId;

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

    @Column(name = "PARENT_ID")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
        if(parentId == null){
              removeValidField("parentId");
              return;
        }
        addValidField("parentId");
    }
}