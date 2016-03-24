package com.xmomen.module.account.service;

import com.xmomen.module.user.entity.SysPermissions;
import org.springframework.beans.factory.annotation.Autowired;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private MybatisDao mybatisDao;

    @Override
    @Transactional
    public SysPermissions createPermission(SysPermissions permission) {
    	permission = mybatisDao.saveByModel(permission);
        return permission;
    }

    @Override
    @Transactional
    public void deletePermission(Long permissionId) {
    	mybatisDao.deleteByPrimaryKey(SysPermissions.class, permissionId);
    }
}
