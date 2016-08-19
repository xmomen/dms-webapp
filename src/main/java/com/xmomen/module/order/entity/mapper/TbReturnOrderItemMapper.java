package com.xmomen.module.order.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.order.entity.TbReturnOrderItem;
import com.xmomen.module.order.entity.TbReturnOrderItemExample;
import org.apache.ibatis.annotations.Param;

public interface TbReturnOrderItemMapper extends MybatisMapper {
    int countByExample(TbReturnOrderItemExample example);

    int deleteByExample(TbReturnOrderItemExample example);

    int insertSelective(TbReturnOrderItem record);

    int updateByExampleSelective(@Param("record") TbReturnOrderItem record, @Param("example") TbReturnOrderItemExample example);
}