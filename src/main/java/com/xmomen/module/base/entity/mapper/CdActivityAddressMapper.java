package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdActivityAddress;
import com.xmomen.module.base.entity.CdActivityAddressExample;
import org.apache.ibatis.annotations.Param;

public interface CdActivityAddressMapper extends MybatisMapper {
    int countByExample(CdActivityAddressExample example);

    int deleteByExample(CdActivityAddressExample example);

    int insertSelective(CdActivityAddress record);

    int updateByExampleSelective(@Param("record") CdActivityAddress record, @Param("example") CdActivityAddressExample example);
}