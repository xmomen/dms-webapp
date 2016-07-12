package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdExpressMember;
import com.xmomen.module.base.entity.CdExpressMemberExample;
import org.apache.ibatis.annotations.Param;

public interface CdExpressMemberMapper extends MybatisMapper {
    int countByExample(CdExpressMemberExample example);

    int deleteByExample(CdExpressMemberExample example);

    int insertSelective(CdExpressMember record);

    int updateByExampleSelective(@Param("record") CdExpressMember record, @Param("example") CdExpressMemberExample example);
}