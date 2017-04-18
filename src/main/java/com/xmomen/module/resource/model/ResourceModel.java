package com.xmomen.module.resource.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xmomen.module.resource.entity.Resource;
import com.xmomen.module.resource.service.ResourceUtilsService;
import lombok.Data;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.*;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

import java.lang.Integer;
import java.lang.String;
import java.io.Serializable;

/**
 * @author tanxinzheng
 * @version 1.0.0
 * @date 2017-4-10 23:26:20
 */
@ExcelTarget(value = "ResourceModel")
public class ResourceModel implements Serializable {

    /**  */
    @NotBlank(message = "为必填项")
    @Length(max = 32, message = "字符长度限制[0,32]")
    private String id;
    /**  */
    @Excel(name = "")
    @Length(max = 50, message = "字符长度限制[0,50]")
    private String entityType;
    /**  */
    @Excel(name = "")
    @Length(max = 100, message = "字符长度限制[0,100]")
    private String entityId;
    /**  */
    @Excel(name = "")
    @Length(max = 255, message = "字符长度限制[0,255]")
    private String path;
    /**  */
    @Excel(name = "")
    @Length(max = 30, message = "字符长度限制[0,30]")
    private String resourceType;
    /**  */
    @Excel(name = "")
    @Range(max = 999999999, min = -999999999, message = "数值范围[999999999,-999999999]")
    private Integer isDefault;

    /**
     * Get Resource Entity Object
     *
     * @return
     */
    @JsonIgnore
    public Resource getEntity() {
        Resource resource = new Resource();
        BeanUtils.copyProperties(this, resource);
        return resource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getPath() {
        return ResourceUtilsService.getWholeHttpPath(this.path);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}
