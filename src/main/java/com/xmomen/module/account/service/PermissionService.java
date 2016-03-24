package com.xmomen.module.account.service;


import com.xmomen.module.user.entity.SysPermissions;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface PermissionService {
    /**
     * 创建权限资源
     * @param permission
     * @return
     */
    public SysPermissions createPermission(SysPermissions permission);

    /**
     * 删除权限资源
     * @param permissionId
     */
    public void deletePermission(Long permissionId);
}
