package com.xmomen.module.pick.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.pick.entity.TbPick;
import com.xmomen.module.pick.entity.TbPickExample;
import org.apache.ibatis.annotations.Param;

public interface TbPickMapper extends MybatisMapper {
    int countByExample(TbPickExample example);

    int deleteByExample(TbPickExample example);

    int insertSelective(TbPick record);

    int updateByExampleSelective(@Param("record") TbPick record, @Param("example") TbPickExample example);
}