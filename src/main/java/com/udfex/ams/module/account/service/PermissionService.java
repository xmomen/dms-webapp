package com.udfex.ams.module.account.service;


import com.udfex.ucs.module.user.entity.SysPermissions;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface PermissionService {
    public SysPermissions createPermission(SysPermissions permission);
    public void deletePermission(Long permissionId);
}
