package com.xmomen.module.account.mapper;

import com.xmomen.module.user.entity.SysPermissions;
import com.xmomen.module.user.entity.SysRoles;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * Created by Jeng on 2016/1/22.
 */
public interface UserMapper {

    public static final String UserMapperNameSpace = "com.xmomen.module.account.mapper.UserMapper.";

    @Select("select r.* from sys_roles r left join sys_users_roles ur on ur.role_id = r.id left join sys_users u on u.id=ur.user_id where u.username = #{username}")
    @ResultType(SysRoles.class)
    public List<SysRoles> getRoleList(String username);

    /**
     * 查询用户所有权限
     * @param username
     * @return
     */
    @Select("select p.* from sys_permissions p left join `sys_roles_permissions` rp on rp.`PERMISSION_ID`=p.`ID` left join sys_roles r on rp.`ROLE_ID`=r.`ID` left join sys_users_roles ur on ur.role_id = r.id left join sys_users u on u.id=ur.user_id where u.username = #{username}")
    @ResultType(SysPermissions.class)
    public List<SysPermissions> getPermissionList(String username);

    /**
     * 修改密码
     * @param username
     * @param currentPassword
     * @param password
     */
    @Update("UPDATE sys_users SET PASSWORD = #{password},SALT=#{salt} WHERE username = #{username} AND PASSWORD=#{currentPassword}")
    public void resetPassword(@Param(value = "username") String username,
                              @Param(value = "currentPassword") String currentPassword,
                              @Param(value = "password") String password,
                              @Param(value = "salt") String salt);
}
