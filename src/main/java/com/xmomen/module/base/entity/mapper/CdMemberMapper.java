package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdMember;
import com.xmomen.module.base.entity.CdMemberExample;
import org.apache.ibatis.annotations.Param;

public interface CdMemberMapper extends MybatisMapper {
    int countByExample(CdMemberExample example);

    int deleteByExample(CdMemberExample example);

    int insertSelective(CdMember record);

    int updateByExampleSelective(@Param("record") CdMember record, @Param("example") CdMemberExample example);
}