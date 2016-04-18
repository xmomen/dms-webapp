package com.xmomen.module.order.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.order.entity.TbOrderRelation;
import com.xmomen.module.order.entity.TbOrderRelationExample;
import org.apache.ibatis.annotations.Param;

public interface TbOrderRelationMapper extends MybatisMapper {
    int countByExample(TbOrderRelationExample example);

    int deleteByExample(TbOrderRelationExample example);

    int insertSelective(TbOrderRelation record);

    int updateByExampleSelective(@Param("record") TbOrderRelation record, @Param("example") TbOrderRelationExample example);
}