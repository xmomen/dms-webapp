package com.xmomen.module.user.entity;

import com.xmomen.framework.mybatis.model.BaseMybatisModel;

import javax.persistence.*;

@Entity
@Table(name = "sys_roles_permissions")
public class SysRolesPermissions extends BaseMybatisModel {
    /**
     * 物理主键
     */
    private Integer id;

    /**
     * 角色表关联主键
     */
    private Integer roleId;

    /**
     * 权限表关联主键
     */
    private Integer permissionId;

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

    @Column(name = "PERMISSION_ID")
    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
        if(permissionId == null){
              removeValidField("permissionId");
              return;
        }
        addValidField("permissionId");
    }
}