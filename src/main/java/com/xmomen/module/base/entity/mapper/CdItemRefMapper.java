package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdItemRef;
import com.xmomen.module.base.entity.CdItemRefExample;
import org.apache.ibatis.annotations.Param;

public interface CdItemRefMapper extends MybatisMapper {
    int countByExample(CdItemRefExample example);

    int deleteByExample(CdItemRefExample example);

    int insertSelective(CdItemRef record);

    int updateByExampleSelective(@Param("record") CdItemRef record, @Param("example") CdItemRefExample example);
}