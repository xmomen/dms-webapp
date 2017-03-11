package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdItemDetail;
import com.xmomen.module.base.entity.CdItemDetailExample;
import org.apache.ibatis.annotations.Param;

public interface CdItemDetailMapper extends MybatisMapper {
    int countByExample(CdItemDetailExample example);

    int deleteByExample(CdItemDetailExample example);

    int insertSelective(CdItemDetail record);

    int updateByExampleSelective(@Param("record") CdItemDetail record, @Param("example") CdItemDetailExample example);
}