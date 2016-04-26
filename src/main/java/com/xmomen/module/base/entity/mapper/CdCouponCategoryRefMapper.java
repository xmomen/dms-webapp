package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdCouponCategoryRef;
import com.xmomen.module.base.entity.CdCouponCategoryRefExample;
import org.apache.ibatis.annotations.Param;

public interface CdCouponCategoryRefMapper extends MybatisMapper {
    int countByExample(CdCouponCategoryRefExample example);

    int deleteByExample(CdCouponCategoryRefExample example);

    int insertSelective(CdCouponCategoryRef record);

    int updateByExampleSelective(@Param("record") CdCouponCategoryRef record, @Param("example") CdCouponCategoryRefExample example);
}