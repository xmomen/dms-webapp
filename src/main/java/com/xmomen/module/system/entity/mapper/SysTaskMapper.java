package com.xmomen.module.system.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.system.entity.SysTask;
import com.xmomen.module.system.entity.SysTaskExample;
import org.apache.ibatis.annotations.Param;

public interface SysTaskMapper extends MybatisMapper {
    int countByExample(SysTaskExample example);

    int deleteByExample(SysTaskExample example);

    int insertSelective(SysTask record);

    int updateByExampleSelective(@Param("record") SysTask record, @Param("example") SysTaskExample example);
}