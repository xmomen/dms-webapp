package com.xmomen.module.order.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.order.entity.TbOrderSendLog;
import com.xmomen.module.order.entity.TbOrderSendLogExample;
import org.apache.ibatis.annotations.Param;

public interface TbOrderSendLogMapper extends MybatisMapper {
    int countByExample(TbOrderSendLogExample example);

    int deleteByExample(TbOrderSendLogExample example);

    int insertSelective(TbOrderSendLog record);

    int updateByExampleSelective(@Param("record") TbOrderSendLog record, @Param("example") TbOrderSendLogExample example);
}