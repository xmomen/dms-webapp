package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdCouponRef;
import com.xmomen.module.base.entity.CdCouponRefExample;
import org.apache.ibatis.annotations.Param;

public interface CdCouponRefMapper extends MybatisMapper {
    int countByExample(CdCouponRefExample example);

    int deleteByExample(CdCouponRefExample example);

    int insertSelective(CdCouponRef record);

    int updateByExampleSelective(@Param("record") CdCouponRef record, @Param("example") CdCouponRefExample example);
}