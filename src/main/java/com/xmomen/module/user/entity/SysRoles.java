package com.xmomen.module.user.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_roles")
public class SysRoles extends BaseMybatisModel {
    /**
     * 物理主键
     */
    private Integer id;

    /**
     * 角色
     */
    private String role;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 是否启用：0-禁用，1-启用
     */
    private Integer available;

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

    @Column(name = "ROLE")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
        if(role == null){
              removeValidField("role");
              return;
        }
        addValidField("role");
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

    @Column(name = "AVAILABLE")
    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
        if(available == null){
              removeValidField("available");
              return;
        }
        addValidField("available");
    }
}