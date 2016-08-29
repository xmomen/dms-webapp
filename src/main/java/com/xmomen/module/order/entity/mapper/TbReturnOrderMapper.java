package com.xmomen.module.order.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.order.entity.TbReturnOrder;
import com.xmomen.module.order.entity.TbReturnOrderExample;
import org.apache.ibatis.annotations.Param;

public interface TbReturnOrderMapper extends MybatisMapper {
    int countByExample(TbReturnOrderExample example);

    int deleteByExample(TbReturnOrderExample example);

    int insertSelective(TbReturnOrder record);

    int updateByExampleSelective(@Param("record") TbReturnOrder record, @Param("example") TbReturnOrderExample example);
}