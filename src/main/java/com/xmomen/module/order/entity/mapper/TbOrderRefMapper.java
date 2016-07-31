package com.xmomen.module.order.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.order.entity.TbOrderRef;
import com.xmomen.module.order.entity.TbOrderRefExample;
import org.apache.ibatis.annotations.Param;

public interface TbOrderRefMapper extends MybatisMapper {
    int countByExample(TbOrderRefExample example);

    int deleteByExample(TbOrderRefExample example);

    int insertSelective(TbOrderRef record);

    int updateByExampleSelective(@Param("record") TbOrderRef record, @Param("example") TbOrderRefExample example);
}