package com.xmomen.module.order.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.order.entity.TbOrderItem;
import com.xmomen.module.order.entity.TbOrderItemExample;
import org.apache.ibatis.annotations.Param;

public interface TbOrderItemMapper extends MybatisMapper {
    int countByExample(TbOrderItemExample example);

    int deleteByExample(TbOrderItemExample example);

    int insertSelective(TbOrderItem record);

    int updateByExampleSelective(@Param("record") TbOrderItem record, @Param("example") TbOrderItemExample example);
}