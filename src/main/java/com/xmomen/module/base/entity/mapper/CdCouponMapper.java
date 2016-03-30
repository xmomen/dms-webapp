package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdCoupon;
import com.xmomen.module.base.entity.CdCouponExample;
import org.apache.ibatis.annotations.Param;

public interface CdCouponMapper extends MybatisMapper {
    int countByExample(CdCouponExample example);

    int deleteByExample(CdCouponExample example);

    int insertSelective(CdCoupon record);

    int updateByExampleSelective(@Param("record") CdCoupon record, @Param("example") CdCouponExample example);
}