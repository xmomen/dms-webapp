package com.xmomen.module.stockdaily.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.stockdaily.entity.StockDaily;
import com.xmomen.module.stockdaily.entity.StockDailyExample;
import org.apache.ibatis.annotations.Param;

public interface StockDailyMapper extends MybatisMapper {
    int countByExample(StockDailyExample example);

    int deleteByExample(StockDailyExample example);

    int insertSelective(StockDaily record);

    int updateByExampleSelective(@Param("record") StockDaily record, @Param("example") StockDailyExample example);
}