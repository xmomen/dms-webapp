package com.xmomen.module.resource.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.resource.entity.Resource;
import com.xmomen.module.resource.entity.ResourceExample;
import org.apache.ibatis.annotations.Param;

public interface ResourceMapper extends MybatisMapper {
    int countByExample(ResourceExample example);

    int deleteByExample(ResourceExample example);

    int insertSelective(Resource record);

    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceExample example);
}