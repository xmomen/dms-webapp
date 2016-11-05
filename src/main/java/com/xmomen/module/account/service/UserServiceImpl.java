package com.xmomen.module.account.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.account.mapper.UserMapper;
import com.xmomen.module.account.model.CreateUser;
import com.xmomen.module.account.web.controller.vo.UpdateUserVo;
import com.xmomen.module.system.entity.SysUserOrganization;
import com.xmomen.module.system.entity.SysUserOrganizationExample;
import com.xmomen.module.user.entity.SysPermissions;
import com.xmomen.module.user.entity.SysRoles;
import com.xmomen.module.user.entity.SysUsers;
import com.xmomen.module.user.entity.SysUsersExample;
import com.xmomen.module.user.entity.SysUsersRoles;
import com.xmomen.module.user.entity.SysUsersRolesExample;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {


    private PasswordHelper passwordHelper;

    public void setPasswordHelper(PasswordHelper passwordHelper) {
        this.passwordHelper = passwordHelper;
    }

    @Autowired
    MybatisDao mybatisDao;

    @Autowired(required = false)
    UserMapper userMapper;

    /**
     * 创建用户
     * @param user
     */
    @Transactional
    public SysUsers createUser(CreateUser user) {
        //加密密码
        String salt = passwordHelper.getSalt();
        String newPassword = passwordHelper.encryptPassword(user.getPassword(), salt);
        SysUsers sysUsers = new SysUsers();
        sysUsers.setSalt(UUID.randomUUID().toString().toUpperCase());
        sysUsers.setUsername(user.getUsername());
        sysUsers.setEmail(user.getEmail());
        sysUsers.setRealname(user.getRealname());
        sysUsers.setAge(user.getAge());
        sysUsers.setOfficeTel(user.getOfficeTel());
        sysUsers.setPhoneNumber(user.getPhoneNumber());
        sysUsers.setQq(user.getQq());
        sysUsers.setSex(user.getSex());
        sysUsers.setSalt(salt);
        sysUsers.setPassword(newPassword);
        sysUsers.setLocked(user.getLocked() ? 1 : 0);
        sysUsers = mybatisDao.saveByModel(sysUsers);
        for(int userGroupId : user.getUserGroupIds()){
	        SysUsersRoles userRoles = new SysUsersRoles();
	        userRoles.setRoleId(userGroupId);
	        userRoles.setUserId(sysUsers.getId());
	        mybatisDao.save(userRoles);
        }
        SysUserOrganization userOrganization = new SysUserOrganization();
        userOrganization.setOrganizationId(user.getOrganizationId());
        userOrganization.setUserId(sysUsers.getId());
        mybatisDao.save(userOrganization);
        return sysUsers;
    }

    /**
     * 更新用户
     *
     * @param updateUserVo
     */
    @Transactional
    @Override
    public void updateUser(UpdateUserVo updateUserVo) {
        SysUsers sysUsers = new SysUsers();
        sysUsers.setId(updateUserVo.getId());
        sysUsers.setUsername(updateUserVo.getUsername());
        sysUsers.setEmail(updateUserVo.getEmail());
        sysUsers.setLocked(updateUserVo.getLocked() ? 1 : 0);
        sysUsers.setAge(updateUserVo.getAge());
        sysUsers.setOfficeTel(updateUserVo.getOfficeTel());
        sysUsers.setPhoneNumber(updateUserVo.getPhoneNumber());
        sysUsers.setSex(updateUserVo.getSex());
        sysUsers.setQq(updateUserVo.getQq());
        sysUsers.setRealname(updateUserVo.getRealName());
        //更新权限
        SysUsersRolesExample sysUsersRolesExample = new SysUsersRolesExample();
        sysUsersRolesExample.createCriteria().andUserIdEqualTo(sysUsers.getId());
		mybatisDao.deleteByExample(sysUsersRolesExample);
		for(int userGroupId : updateUserVo.getUserGroupIds()){
	        SysUsersRoles userRoles = new SysUsersRoles();
	        userRoles.setRoleId(userGroupId);
	        userRoles.setUserId(sysUsers.getId());
	        mybatisDao.save(userRoles);
        }
        SysUserOrganizationExample sysUserOrganizationExample = new SysUserOrganizationExample();
        sysUserOrganizationExample.createCriteria().andUserIdEqualTo(sysUsers.getId());
		mybatisDao.deleteByExample(sysUserOrganizationExample);
        SysUserOrganization userOrganization = new SysUserOrganization();
        userOrganization.setOrganizationId(updateUserVo.getOrganizationId());
        userOrganization.setUserId(sysUsers.getId());
        mybatisDao.save(userOrganization);
        mybatisDao.save(sysUsers);
    }

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    @Transactional
    public void changePassword(Integer userId, String newPassword) {
        SysUsers user = mybatisDao.selectByPrimaryKey(SysUsers.class, userId);
        String salt = passwordHelper.getSalt();
        newPassword = passwordHelper.encryptPassword(newPassword, salt);
        user.setPassword(newPassword);
        user.setSalt(salt);
        mybatisDao.update(user);
    }

    @Override
    public void changePassword(String username, String currentPassword, String newPassword) {
        SysUsers sysUsers = new SysUsers();
        sysUsers.setUsername(username);
        sysUsers = mybatisDao.selectOneByModel(sysUsers);
        String currentRealPwd = passwordHelper.encryptPassword(currentPassword, sysUsers.getSalt());
        if(sysUsers == null || !sysUsers.getPassword().equals(currentRealPwd)){
            throw new IllegalArgumentException("当前密码错误");
        }
        String newSalt = passwordHelper.getSalt();
        String newCurrentRealPwd = passwordHelper.encryptPassword(newPassword, newSalt);
        userMapper.resetPassword(username, currentRealPwd, newCurrentRealPwd, newSalt);
    }

    /**
     * 添加用户-角色关系
     * @param userId
     * @param roleIds
     */
    @Transactional
    public void correlationRoles(Integer userId, Integer... roleIds) {
        for (int i = 0; i < roleIds.length; i++) {
            Integer roleId = roleIds[i];
            SysUsersRoles sysUsersRoles = new SysUsersRoles();
            sysUsersRoles.setUserId(userId);
            sysUsersRoles.setRoleId(roleId);
            mybatisDao.insert(sysUsersRoles);
        }
    }


    /**
     * 移除用户-角色关系
     * @param userId
     * @param roleIds
     */
    public void uncorrelationRoles(Integer userId, Integer... roleIds) {
        SysUsersRolesExample sysUsersRolesExample = new SysUsersRolesExample();
        sysUsersRolesExample.createCriteria()
                .andUserIdEqualTo(userId)
                .andRoleIdIn(Arrays.asList(roleIds));
        mybatisDao.deleteByExample(sysUsersRolesExample);
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public SysUsers findByUsername(String username) {
        SysUsersExample sysUsersExample = new SysUsersExample();
        sysUsersExample.createCriteria().andUsernameEqualTo(username);
        sysUsersExample.or().andEmailEqualTo(username);
        sysUsersExample.or().andPhoneNumberEqualTo(username);
        return mybatisDao.selectOneByExample(sysUsersExample);
    }

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) {
        List<SysRoles> sysRolesList = userMapper.getRoleList(username);
        Set<String> roles = new HashSet();
        for (int i = 0; i < sysRolesList.size(); i++) {
            roles.add(sysRolesList.get(i).getRole());
        }
        return roles;
    }

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) {
        List<SysPermissions> sysPermissionsList = userMapper.getPermissionList(username);
        Set<String> permissions = new HashSet();
        for (int i = 0; i < sysPermissionsList.size(); i++) {
            permissions.add(sysPermissionsList.get(i).getPermission());
        }
        return permissions;
    }

}
