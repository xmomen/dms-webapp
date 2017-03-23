package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdMemberAddress;
import com.xmomen.module.base.entity.CdMemberAddressExample;
import org.apache.ibatis.annotations.Param;

public interface CdMemberAddressMapper extends MybatisMapper {
    int countByExample(CdMemberAddressExample example);

    int deleteByExample(CdMemberAddressExample example);

    int insertSelective(CdMemberAddress record);

    int updateByExampleSelective(@Param("record") CdMemberAddress record, @Param("example") CdMemberAddressExample example);
}