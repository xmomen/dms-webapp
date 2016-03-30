package com.xmomen.module.user.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.user.entity.SysUsers;
import com.xmomen.module.user.entity.SysUsersExample;
import org.apache.ibatis.annotations.Param;

public interface SysUsersMapper extends MybatisMapper {
    int countByExample(SysUsersExample example);

    int deleteByExample(SysUsersExample example);

    int insertSelective(SysUsers record);

    int updateByExampleSelective(@Param("record") SysUsers record, @Param("example") SysUsersExample example);
}