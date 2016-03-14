package com.udfex.ams.module.account.mapper;

import com.udfex.ucs.module.user.entity.SysRoles;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


/**
 * Created by Jeng on 2016/1/22.
 */
public interface UserMapper {

    @Select("select r.* from sys_roles r left join sys_users_roles ur on ur.role_id = r.id left join sys_users u on u.id=ur.user_id where u.username = #{username}")
    @ResultType(SysRoles.class)
    public List<SysRoles> getRoleList(String username);

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
