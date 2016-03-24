package com.xmomen.module.user.entity.mapper;

import com.xmomen.module.user.entity.SysRoles;
import com.xmomen.module.user.entity.SysRolesExample;
import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import org.apache.ibatis.annotations.Param;

public interface SysRolesMapper extends MybatisMapper {
    int countByExample(SysRolesExample example);

    int deleteByExample(SysRolesExample example);

    int insertSelective(SysRoles record);

    int updateByExampleSelective(@Param("record") SysRoles record, @Param("example") SysRolesExample example);
}