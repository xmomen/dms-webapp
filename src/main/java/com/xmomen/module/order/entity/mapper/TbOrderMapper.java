package com.xmomen.module.order.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.order.entity.TbOrderExample;
import org.apache.ibatis.annotations.Param;

public interface TbOrderMapper extends MybatisMapper {
    int countByExample(TbOrderExample example);

    int deleteByExample(TbOrderExample example);

    int insertSelective(TbOrder record);

    int updateByExampleSelective(@Param("record") TbOrder record, @Param("example") TbOrderExample example);
}