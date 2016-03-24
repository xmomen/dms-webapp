package com.xmomen.module.account.service;

import com.xmomen.module.account.mapper.UserMapper;
import com.xmomen.module.account.web.controller.vo.GroupPermissionRelation;
import com.xmomen.module.account.web.controller.vo.UserGroupRelation;
import com.xmomen.module.user.entity.SysRoles;
import com.xmomen.module.user.entity.SysRolesExample;
import com.xmomen.module.user.entity.SysRolesPermissions;
import com.xmomen.module.user.entity.SysRolesPermissionsExample;
import com.xmomen.framework.mybatis.page.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private MybatisDao mybatisDao;

	@Autowired
	UserService userService;

	/**
	 * 根据角色ID查询用户
	 *
	 * @param roleId
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	@Override
	public Page<UserGroupRelation> findUsersByRoles(String roleId, boolean chose, Integer pageSize, Integer pageNum) {
		Map map = new HashMap();
		map.put("id" , roleId);
		map.put("chose" , chose);
		return (Page<UserGroupRelation>) mybatisDao.selectPage(UserMapper.UserMapperNameSpace + "findUsersByRolesId", map, pageSize, pageNum);
	}

	@Override
	public Page<SysRoles> findRoles(String keyValue, Integer pageSize, Integer pageNum) {
		SysRolesExample sysRolesExample = new SysRolesExample();
		if(StringUtils.isNotBlank(keyValue)){
			sysRolesExample.createCriteria()
					.andRoleLike(keyValue);
			sysRolesExample.or().andDescriptionLike(keyValue);
		}
		return mybatisDao.selectPageByExample(sysRolesExample, pageSize, pageNum);
	}

	/**
	 * 获取角色
	 *
	 * @param roleId
	 */
	@Override
	public SysRoles getRole(Integer roleId) {
		return mybatisDao.selectByPrimaryKey(SysRoles.class, roleId);
	}

	/**
	 * 更新角色
	 *
	 * @param sysRoles
	 */
	@Override
	public void updateRole(SysRoles sysRoles) {
		mybatisDao.update(sysRoles);
	}

	/**
	 * 更新用户组信息并添加用户
	 *
	 * @param sysRoles
	 * @param userIdList
	 */
	@Override
	@Transactional
	public void updateRole(SysRoles sysRoles, List<Integer> userIdList) {
		mybatisDao.update(sysRoles);
		for (Integer userId : userIdList) {
			userService.correlationRoles(userId, sysRoles.getId());
		}
	}


	public SysRoles createRole(SysRoles role) {
    	role = mybatisDao.saveByModel(role);
        return role;
    }

    public void deleteRole(Integer roleId) {
    	mybatisDao.deleteByPrimaryKey(SysRoles.class, roleId);
    }

	@Override
	public Page<GroupPermissionRelation> findPermissionByRoles(String roleId, boolean chose, Integer pageSize, Integer pageNum) {
		Map map = new HashMap();
		map.put("id" , roleId);
		map.put("chose" , chose);
		return (Page<GroupPermissionRelation>) mybatisDao.selectPage(UserMapper.UserMapperNameSpace + "findPermissionByRole", map, pageSize, pageNum);
	}

	/**
     * 添加角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void correlationPermissions(Integer roleId, Integer... permissionIds) {
    	for(Integer permissionId : permissionIds){
    		SysRolesPermissions sysRolesPermissionsKey = new SysRolesPermissions();
    		sysRolesPermissionsKey.setPermissionId(permissionId);
    		sysRolesPermissionsKey.setRoleId(roleId);
    		mybatisDao.save(sysRolesPermissionsKey);
    	}
    }

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param permissionIds
     */
    public void unCorrelationPermissions(Integer roleId, Integer... permissionIds) {
		SysRolesPermissionsExample sysRolesPermissionsExample = new SysRolesPermissionsExample();
		sysRolesPermissionsExample.createCriteria()
				.andRoleIdEqualTo(roleId)
				.andPermissionIdIn(Arrays.asList(permissionIds));
		mybatisDao.deleteByExample(sysRolesPermissionsExample);
    }

}
