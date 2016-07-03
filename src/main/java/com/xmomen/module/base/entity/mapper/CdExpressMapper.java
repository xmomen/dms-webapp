package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdExpress;
import com.xmomen.module.base.entity.CdExpressExample;
import org.apache.ibatis.annotations.Param;

public interface CdExpressMapper extends MybatisMapper {
    int countByExample(CdExpressExample example);

    int deleteByExample(CdExpressExample example);

    int insertSelective(CdExpress record);

    int updateByExampleSelective(@Param("record") CdExpress record, @Param("example") CdExpressExample example);
}