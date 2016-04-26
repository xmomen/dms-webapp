package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdActivity;
import com.xmomen.module.base.entity.CdActivityExample;
import org.apache.ibatis.annotations.Param;

public interface CdActivityMapper extends MybatisMapper {
    int countByExample(CdActivityExample example);

    int deleteByExample(CdActivityExample example);

    int insertSelective(CdActivity record);

    int updateByExampleSelective(@Param("record") CdActivity record, @Param("example") CdActivityExample example);
}