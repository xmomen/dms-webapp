package com.xmomen.module.user.entity.mapper;

import com.xmomen.module.user.entity.SysPermissions;
import com.xmomen.module.user.entity.SysPermissionsExample;
import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import org.apache.ibatis.annotations.Param;

public interface SysPermissionsMapper extends MybatisMapper {
    int countByExample(SysPermissionsExample example);

    int deleteByExample(SysPermissionsExample example);

    int insertSelective(SysPermissions record);

    int updateByExampleSelective(@Param("record") SysPermissions record, @Param("example") SysPermissionsExample example);
}