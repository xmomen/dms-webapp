package com.xmomen.module.receipt.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.receipt.entity.TbReceivingCodeRequest;
import com.xmomen.module.receipt.entity.TbReceivingCodeRequestExample;
import org.apache.ibatis.annotations.Param;

public interface TbReceivingCodeRequestMapper extends MybatisMapper {
    int countByExample(TbReceivingCodeRequestExample example);

    int deleteByExample(TbReceivingCodeRequestExample example);

    int insertSelective(TbReceivingCodeRequest record);

    int updateByExampleSelective(@Param("record") TbReceivingCodeRequest record, @Param("example") TbReceivingCodeRequestExample example);
}