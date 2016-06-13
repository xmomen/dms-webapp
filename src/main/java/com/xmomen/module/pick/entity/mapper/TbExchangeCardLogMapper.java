package com.xmomen.module.pick.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.pick.entity.TbExchangeCardLog;
import com.xmomen.module.pick.entity.TbExchangeCardLogExample;
import org.apache.ibatis.annotations.Param;

public interface TbExchangeCardLogMapper extends MybatisMapper {
    int countByExample(TbExchangeCardLogExample example);

    int deleteByExample(TbExchangeCardLogExample example);

    int insertSelective(TbExchangeCardLog record);

    int updateByExampleSelective(@Param("record") TbExchangeCardLog record, @Param("example") TbExchangeCardLogExample example);
}