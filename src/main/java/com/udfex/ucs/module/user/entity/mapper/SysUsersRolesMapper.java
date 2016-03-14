package com.udfex.ucs.module.user.entity.mapper;

import com.udfex.ucs.module.user.entity.SysUsersRoles;
import com.udfex.ucs.module.user.entity.SysUsersRolesExample;
import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import org.apache.ibatis.annotations.Param;

public interface SysUsersRolesMapper extends MybatisMapper {
    int countByExample(SysUsersRolesExample example);

    int deleteByExample(SysUsersRolesExample example);

    int insertSelective(SysUsersRoles record);

    int updateByExampleSelective(@Param("record") SysUsersRoles record, @Param("example") SysUsersRolesExample example);
}