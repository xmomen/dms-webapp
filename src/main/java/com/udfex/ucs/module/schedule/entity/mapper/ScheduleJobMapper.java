package com.udfex.ucs.module.schedule.entity.mapper;

import com.udfex.ucs.module.schedule.entity.ScheduleJob;
import com.udfex.ucs.module.schedule.entity.ScheduleJobExample;
import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import org.apache.ibatis.annotations.Param;

public interface ScheduleJobMapper extends MybatisMapper {
    int countByExample(ScheduleJobExample example);

    int deleteByExample(ScheduleJobExample example);

    int insertSelective(ScheduleJob record);

    int updateByExampleSelective(@Param("record") ScheduleJob record, @Param("example") ScheduleJobExample example);
}