package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdActivityRef;
import com.xmomen.module.base.entity.CdActivityRefExample;
import org.apache.ibatis.annotations.Param;

public interface CdActivityRefMapper extends MybatisMapper {
    int countByExample(CdActivityRefExample example);

    int deleteByExample(CdActivityRefExample example);

    int insertSelective(CdActivityRef record);

    int updateByExampleSelective(@Param("record") CdActivityRef record, @Param("example") CdActivityRefExample example);
}