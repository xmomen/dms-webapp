package com.xmomen.module.system.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "sys_user_organization")
public class SysUserOrganization extends BaseMybatisModel {
    /**
     * 
     */
    private Integer id;

    /**
     * 部门
     */
    private Integer organizationId;

    /**
     * 用户
     */
    private Integer userId;

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

    @Column(name = "ORGANIZATION_ID")
    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
        if(organizationId == null){
              removeValidField("organizationId");
              return;
        }
        addValidField("organizationId");
    }

    @Column(name = "USER_ID")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
        if(userId == null){
              removeValidField("userId");
              return;
        }
        addValidField("userId");
    }
}