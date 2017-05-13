package com.xmomen.module.stock.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.stock.entity.Stock;
import com.xmomen.module.stock.entity.StockExample;
import org.apache.ibatis.annotations.Param;

public interface StockMapper extends MybatisMapper {
    int countByExample(StockExample example);

    int deleteByExample(StockExample example);

    int insertSelective(Stock record);

    int updateByExampleSelective(@Param("record") Stock record, @Param("example") StockExample example);
}