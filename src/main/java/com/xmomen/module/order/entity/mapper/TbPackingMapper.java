package com.xmomen.module.order.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.order.entity.TbPacking;
import com.xmomen.module.order.entity.TbPackingExample;
import org.apache.ibatis.annotations.Param;

public interface TbPackingMapper extends MybatisMapper {
    int countByExample(TbPackingExample example);

    int deleteByExample(TbPackingExample example);

    int insertSelective(TbPacking record);

    int updateByExampleSelective(@Param("record") TbPacking record, @Param("example") TbPackingExample example);
}