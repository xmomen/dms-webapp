package com.xmomen.module.stock.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.stock.entity.StockRecord;
import com.xmomen.module.stock.entity.StockRecordExample;
import org.apache.ibatis.annotations.Param;

public interface StockRecordMapper extends MybatisMapper {
    int countByExample(StockRecordExample example);

    int deleteByExample(StockRecordExample example);

    int insertSelective(StockRecord record);

    int updateByExampleSelective(@Param("record") StockRecord record, @Param("example") StockRecordExample example);
}