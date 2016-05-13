package com.xmomen.module.job.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.job.entity.TbJob;
import com.xmomen.module.job.entity.TbJobExample;
import org.apache.ibatis.annotations.Param;

public interface TbJobMapper extends MybatisMapper {
    int countByExample(TbJobExample example);

    int deleteByExample(TbJobExample example);

    int insertSelective(TbJob record);

    int updateByExampleSelective(@Param("record") TbJob record, @Param("example") TbJobExample example);
}