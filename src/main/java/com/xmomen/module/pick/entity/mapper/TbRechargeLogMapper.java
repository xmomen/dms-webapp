package com.xmomen.module.pick.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.pick.entity.TbRechargeLog;
import com.xmomen.module.pick.entity.TbRechargeLogExample;
import org.apache.ibatis.annotations.Param;

public interface TbRechargeLogMapper extends MybatisMapper {
    int countByExample(TbRechargeLogExample example);

    int deleteByExample(TbRechargeLogExample example);

    int insertSelective(TbRechargeLog record);

    int updateByExampleSelective(@Param("record") TbRechargeLog record, @Param("example") TbRechargeLogExample example);
}