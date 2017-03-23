package com.xmomen.module.member.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.member.entity.MemberAddress;
import com.xmomen.module.member.entity.MemberAddressExample;
import org.apache.ibatis.annotations.Param;

public interface MemberAddressMapper extends MybatisMapper {
    int countByExample(MemberAddressExample example);

    int deleteByExample(MemberAddressExample example);

    int insertSelective(MemberAddress record);

    int updateByExampleSelective(@Param("record") MemberAddress record, @Param("example") MemberAddressExample example);
}