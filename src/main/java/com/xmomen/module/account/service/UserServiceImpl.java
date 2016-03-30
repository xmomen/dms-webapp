package com.xmomen.module.account.service;

import java.util.*;

import com.xmomen.module.account.mapper.UserMapper;
import com.xmomen.module.account.model.CreateUser;
import com.xmomen.module.account.web.controller.vo.UpdateUserVo;
import com.xmomen.module.user.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;

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
        String newPassword = passwordHelper.encryptPassword(user.getPassword(), user.getUsername(), salt);
        SysUsers sysUsers = new SysUsers();
        sysUsers.setSalt(UUID.randomUUID().toString().toUpperCase());
        sysUsers.setUsername(user.getUsername());
        sysUsers.setEmail(user.getEmail());
        sysUsers.setSalt(salt);
        sysUsers.setPassword(newPassword);
        sysUsers.setLocked(user.getLocked() ? 1 : 0);
        return mybatisDao.saveByModel(sysUsers);
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
        sysUsers.setSex(updateUserVo.getAge());
        sysUsers.setQq(updateUserVo.getQq());
        sysUsers.setRealname(updateUserVo.getRealName());
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
        user.setPassword(newPassword);
        user.setSalt(salt);
        passwordHelper.encryptPassword(user.getPassword(), user.getUsername(), salt);
        mybatisDao.update(user);
    }

    @Override
    public void changePassword(String username, String currentPassword, String newPassword) {
        SysUsers sysUsers = new SysUsers();
        sysUsers.setUsername(username);
        sysUsers = mybatisDao.selectOneByModel(sysUsers);
        String currentRealPwd = passwordHelper.encryptPassword(currentPassword, sysUsers.getUsername(), sysUsers.getSalt());
        if(sysUsers == null || !sysUsers.getPassword().equals(currentRealPwd)){
            throw new IllegalArgumentException("当前密码错误");
        }
        String newSalt = passwordHelper.getSalt();
        String newCurrentRealPwd = passwordHelper.encryptPassword(newPassword, sysUsers.getUsername(), newSalt);
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
        SysUsers sysUsers = new SysUsers();
        sysUsers.setUsername(username);
        return mybatisDao.selectOneByModel(sysUsers);
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
