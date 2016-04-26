package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdCouponCategory;
import com.xmomen.module.base.entity.CdCouponCategoryExample;
import org.apache.ibatis.annotations.Param;

public interface CdCouponCategoryMapper extends MybatisMapper {
    int countByExample(CdCouponCategoryExample example);

    int deleteByExample(CdCouponCategoryExample example);

    int insertSelective(CdCouponCategory record);

    int updateByExampleSelective(@Param("record") CdCouponCategory record, @Param("example") CdCouponCategoryExample example);
}