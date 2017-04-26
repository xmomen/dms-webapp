package com.xmomen.module.payrecord.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.payrecord.entity.PayRecord;
import com.xmomen.module.payrecord.entity.PayRecordExample;
import org.apache.ibatis.annotations.Param;

public interface PayRecordMapper extends MybatisMapper {
    int countByExample(PayRecordExample example);

    int deleteByExample(PayRecordExample example);

    int insertSelective(PayRecord record);

    int updateByExampleSelective(@Param("record") PayRecord record, @Param("example") PayRecordExample example);
}