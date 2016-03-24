package com.xmomen.module.account.web.controller.vo;

import java.io.Serializable;

/**
 * Created by Jeng on 2016/2/1.
 */
public class GroupPermissionRelation implements Serializable {

    private String groupId;
    private String permissionCode;
    private String permissionDescription;
    private String permissionId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}
