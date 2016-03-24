package com.xmomen.module.user.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;

import javax.persistence.*;

@Entity
@Table(name = "sys_users_roles")
public class SysUsersRoles extends BaseMybatisModel {
    /**
     * 物理主键
     */
    private Integer id;

    /**
     * 用户表关联主键
     */
    private Integer userId;

    /**
     * 角色表关联主键
     */
    private Integer roleId;

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

    @Column(name = "ROLE_ID")
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
        if(roleId == null){
              removeValidField("roleId");
              return;
        }
        addValidField("roleId");
    }
}