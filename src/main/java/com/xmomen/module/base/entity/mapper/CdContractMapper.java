package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdContract;
import com.xmomen.module.base.entity.CdContractExample;
import org.apache.ibatis.annotations.Param;

public interface CdContractMapper extends MybatisMapper {
    int countByExample(CdContractExample example);

    int deleteByExample(CdContractExample example);

    int insertSelective(CdContract record);

    int updateByExampleSelective(@Param("record") CdContract record, @Param("example") CdContractExample example);
}