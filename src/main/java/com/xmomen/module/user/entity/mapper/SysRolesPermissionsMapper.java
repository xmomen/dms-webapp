package com.xmomen.module.user.entity.mapper;

import com.xmomen.module.user.entity.SysRolesPermissionsExample;
import com.xmomen.module.user.entity.SysRolesPermissions;
import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import org.apache.ibatis.annotations.Param;

public interface SysRolesPermissionsMapper extends MybatisMapper {
    int countByExample(SysRolesPermissionsExample example);

    int deleteByExample(SysRolesPermissionsExample example);

    int insertSelective(SysRolesPermissions record);

    int updateByExampleSelective(@Param("record") SysRolesPermissions record, @Param("example") SysRolesPermissionsExample example);
}