package com.xmomen.module.advice.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.advice.entity.Advice;
import com.xmomen.module.advice.entity.AdviceExample;
import org.apache.ibatis.annotations.Param;

public interface AdviceMapper extends MybatisMapper {
    int countByExample(AdviceExample example);

    int deleteByExample(AdviceExample example);

    int insertSelective(Advice record);

    int updateByExampleSelective(@Param("record") Advice record, @Param("example") AdviceExample example);
}