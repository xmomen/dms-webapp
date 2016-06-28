package com.xmomen.module.order.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.order.entity.TbTradeRecord;
import com.xmomen.module.order.entity.TbTradeRecordExample;
import org.apache.ibatis.annotations.Param;

public interface TbTradeRecordMapper extends MybatisMapper {
    int countByExample(TbTradeRecordExample example);

    int deleteByExample(TbTradeRecordExample example);

    int insertSelective(TbTradeRecord record);

    int updateByExampleSelective(@Param("record") TbTradeRecord record, @Param("example") TbTradeRecordExample example);
}