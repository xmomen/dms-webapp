package com.xmomen.module.job.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.job.entity.TbJobOperationLog;
import com.xmomen.module.job.entity.TbJobOperationLogExample;
import org.apache.ibatis.annotations.Param;

public interface TbJobOperationLogMapper extends MybatisMapper {
    int countByExample(TbJobOperationLogExample example);

    int deleteByExample(TbJobOperationLogExample example);

    int insertSelective(TbJobOperationLog record);

    int updateByExampleSelective(@Param("record") TbJobOperationLog record, @Param("example") TbJobOperationLogExample example);
}