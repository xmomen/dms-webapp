package com.xmomen.module.beforehandpackagerecord.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.beforehandpackagerecord.entity.BeforehandPackageRecord;
import com.xmomen.module.beforehandpackagerecord.entity.BeforehandPackageRecordExample;
import org.apache.ibatis.annotations.Param;

public interface BeforehandPackageRecordMapper extends MybatisMapper {
    int countByExample(BeforehandPackageRecordExample example);

    int deleteByExample(BeforehandPackageRecordExample example);

    int insertSelective(BeforehandPackageRecord record);

    int updateByExampleSelective(@Param("record") BeforehandPackageRecord record, @Param("example") BeforehandPackageRecordExample example);
}