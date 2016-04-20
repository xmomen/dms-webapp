package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdMemberCouponRelation;
import com.xmomen.module.base.entity.CdMemberCouponRelationExample;
import org.apache.ibatis.annotations.Param;

public interface CdMemberCouponRelationMapper extends MybatisMapper {
    int countByExample(CdMemberCouponRelationExample example);

    int deleteByExample(CdMemberCouponRelationExample example);

    int insertSelective(CdMemberCouponRelation record);

    int updateByExampleSelective(@Param("record") CdMemberCouponRelation record, @Param("example") CdMemberCouponRelationExample example);
}