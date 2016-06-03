package com.xmomen.module.system.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.system.entity.SysTaskHead;
import com.xmomen.module.system.entity.SysTaskHeadExample;
import org.apache.ibatis.annotations.Param;

public interface SysTaskHeadMapper extends MybatisMapper {
    int countByExample(SysTaskHeadExample example);

    int deleteByExample(SysTaskHeadExample example);

    int insertSelective(SysTaskHead record);

    int updateByExampleSelective(@Param("record") SysTaskHead record, @Param("example") SysTaskHeadExample example);
}