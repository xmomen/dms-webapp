package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdCategory;
import com.xmomen.module.base.entity.CdCategoryExample;
import org.apache.ibatis.annotations.Param;

public interface CdCategoryMapper extends MybatisMapper {
    int countByExample(CdCategoryExample example);

    int deleteByExample(CdCategoryExample example);

    int insertSelective(CdCategory record);

    int updateByExampleSelective(@Param("record") CdCategory record, @Param("example") CdCategoryExample example);
}