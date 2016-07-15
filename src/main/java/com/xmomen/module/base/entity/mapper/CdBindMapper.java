package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdBind;
import com.xmomen.module.base.entity.CdBindExample;
import org.apache.ibatis.annotations.Param;

public interface CdBindMapper extends MybatisMapper {
    int countByExample(CdBindExample example);

    int deleteByExample(CdBindExample example);

    int insertSelective(CdBind record);

    int updateByExampleSelective(@Param("record") CdBind record, @Param("example") CdBindExample example);
}