package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdPlanItem;
import com.xmomen.module.base.entity.CdPlanItemExample;
import org.apache.ibatis.annotations.Param;

public interface CdPlanItemMapper extends MybatisMapper {
    int countByExample(CdPlanItemExample example);

    int deleteByExample(CdPlanItemExample example);

    int insertSelective(CdPlanItem record);

    int updateByExampleSelective(@Param("record") CdPlanItem record, @Param("example") CdPlanItemExample example);
}