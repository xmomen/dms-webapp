package com.xmomen.module.user.entity.mapper;

import com.xmomen.module.user.entity.SysUsersRoles;
import com.xmomen.module.user.entity.SysUsersRolesExample;
import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import org.apache.ibatis.annotations.Param;

public interface SysUsersRolesMapper extends MybatisMapper {
    int countByExample(SysUsersRolesExample example);

    int deleteByExample(SysUsersRolesExample example);

    int insertSelective(SysUsersRoles record);

    int updateByExampleSelective(@Param("record") SysUsersRoles record, @Param("example") SysUsersRolesExample example);
}