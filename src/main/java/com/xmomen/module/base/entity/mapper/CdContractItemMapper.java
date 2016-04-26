package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdContractItem;
import com.xmomen.module.base.entity.CdContractItemExample;
import org.apache.ibatis.annotations.Param;

public interface CdContractItemMapper extends MybatisMapper {
    int countByExample(CdContractItemExample example);

    int deleteByExample(CdContractItemExample example);

    int insertSelective(CdContractItem record);

    int updateByExampleSelective(@Param("record") CdContractItem record, @Param("example") CdContractItemExample example);
}