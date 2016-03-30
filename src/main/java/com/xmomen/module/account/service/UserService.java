package com.xmomen.module.account.service;

import java.util.Set;

import com.xmomen.module.account.model.CreateUser;
import com.xmomen.module.account.web.controller.vo.UpdateUserVo;
import com.xmomen.module.user.entity.SysUsers;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface UserService {

    /**
     * 创建用户
     * @param user
     */
    public SysUsers createUser(CreateUser user);

    /**
     * 更新用户
     * @param updateUserVo
     */
    public void updateUser(UpdateUserVo updateUserVo);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(Integer userId, String newPassword);

    /**
     * 修改密码
     * @param currentPassword
     * @param newPassword
     */
    public void changePassword(String username, String currentPassword, String newPassword);

    /**
     * 添加用户-角色关系
     * @param userId
     * @param roleIds
     */
    public void correlationRoles(Integer userId, Integer... roleIds);


    /**
     * 移除用户-角色关系
     * @param userId
     * @param roleIds
     */
    public void uncorrelationRoles(Integer userId, Integer... roleIds);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public SysUsers findByUsername(String username);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);

}
