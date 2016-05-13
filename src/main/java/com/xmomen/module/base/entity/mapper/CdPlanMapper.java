package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdPlan;
import com.xmomen.module.base.entity.CdPlanExample;
import org.apache.ibatis.annotations.Param;

public interface CdPlanMapper extends MybatisMapper {
    int countByExample(CdPlanExample example);

    int deleteByExample(CdPlanExample example);

    int insertSelective(CdPlan record);

    int updateByExampleSelective(@Param("record") CdPlan record, @Param("example") CdPlanExample example);
}