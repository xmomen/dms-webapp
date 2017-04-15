package com.xmomen.module.resource.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "cd_resource")
public class Resource extends BaseMybatisModel {
    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private String entityType;

    /**
     * 
     */
    private String entityId;

    /**
     * 
     */
    private String path;

    /**
     * 
     */
    private String resourceType;

    /**
     * 
     */
    private Integer isDefault;

    @Column(name = "ID")
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        if(id == null){
              removeValidField("id");
              return;
        }
        addValidField("id");
    }

    @Column(name = "ENTITY_TYPE")
    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
        if(entityType == null){
              removeValidField("entityType");
              return;
        }
        addValidField("entityType");
    }

    @Column(name = "ENTITY_ID")
    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
        if(entityId == null){
              removeValidField("entityId");
              return;
        }
        addValidField("entityId");
    }

    @Column(name = "PATH")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        if(path == null){
              removeValidField("path");
              return;
        }
        addValidField("path");
    }

    @Column(name = "RESOURCE_TYPE")
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
        if(resourceType == null){
              removeValidField("resourceType");
              return;
        }
        addValidField("resourceType");
    }

    @Column(name = "IS_DEFAULT")
    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
        if(isDefault == null){
              removeValidField("isDefault");
              return;
        }
        addValidField("isDefault");
    }
}