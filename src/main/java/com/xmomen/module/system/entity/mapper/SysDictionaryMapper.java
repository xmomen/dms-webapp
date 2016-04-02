package com.xmomen.module.system.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.system.entity.SysDictionary;
import com.xmomen.module.system.entity.SysDictionaryExample;
import org.apache.ibatis.annotations.Param;

public interface SysDictionaryMapper extends MybatisMapper {
    int countByExample(SysDictionaryExample example);

    int deleteByExample(SysDictionaryExample example);

    int insertSelective(SysDictionary record);

    int updateByExampleSelective(@Param("record") SysDictionary record, @Param("example") SysDictionaryExample example);
}