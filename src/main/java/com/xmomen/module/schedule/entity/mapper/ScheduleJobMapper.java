package com.xmomen.module.schedule.entity.mapper;

import com.xmomen.module.schedule.entity.ScheduleJob;
import com.xmomen.module.schedule.entity.ScheduleJobExample;
import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import org.apache.ibatis.annotations.Param;

public interface ScheduleJobMapper extends MybatisMapper {
    int countByExample(ScheduleJobExample example);

    int deleteByExample(ScheduleJobExample example);

    int insertSelective(ScheduleJob record);

    int updateByExampleSelective(@Param("record") ScheduleJob record, @Param("example") ScheduleJobExample example);
}