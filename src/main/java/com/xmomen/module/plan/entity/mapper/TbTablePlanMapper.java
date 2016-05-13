package com.xmomen.module.plan.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.plan.entity.TbTablePlan;
import com.xmomen.module.plan.entity.TbTablePlanExample;
import org.apache.ibatis.annotations.Param;

public interface TbTablePlanMapper extends MybatisMapper {
    int countByExample(TbTablePlanExample example);

    int deleteByExample(TbTablePlanExample example);

    int insertSelective(TbTablePlan record);

    int updateByExampleSelective(@Param("record") TbTablePlan record, @Param("example") TbTablePlanExample example);
}