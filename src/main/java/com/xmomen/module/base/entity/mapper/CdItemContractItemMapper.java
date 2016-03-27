package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdItemContractItem;
import com.xmomen.module.base.entity.CdItemContractItemExample;
import org.apache.ibatis.annotations.Param;

public interface CdItemContractItemMapper extends MybatisMapper {
    int countByExample(CdItemContractItemExample example);

    int deleteByExample(CdItemContractItemExample example);

    int insertSelective(CdItemContractItem record);

    int updateByExampleSelective(@Param("record") CdItemContractItem record, @Param("example") CdItemContractItemExample example);
}