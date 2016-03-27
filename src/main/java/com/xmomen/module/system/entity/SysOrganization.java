package com.xmomen.module.system.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "sys_organization")
public class SysOrganization extends BaseMybatisModel {
    /**
     * 物理主键
     */
    private Integer id;

    /**
     * 组织名称
     */
    private String name;

    /**
     * 
     */
    private String description;

    /**
     * 
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

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        if(description == null){
              removeValidField("description");
              return;
        }
        addValidField("description");
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