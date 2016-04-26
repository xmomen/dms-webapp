package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdItem;
import com.xmomen.module.base.entity.CdItemExample;
import org.apache.ibatis.annotations.Param;

public interface CdItemMapper extends MybatisMapper {
    int countByExample(CdItemExample example);

    int deleteByExample(CdItemExample example);

    int insertSelective(CdItem record);

    int updateByExampleSelective(@Param("record") CdItem record, @Param("example") CdItemExample example);
}