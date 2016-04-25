package com.xmomen.module.order.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.order.entity.TbPurchase;
import com.xmomen.module.order.entity.TbPurchaseExample;
import org.apache.ibatis.annotations.Param;

public interface TbPurchaseMapper extends MybatisMapper {
    int countByExample(TbPurchaseExample example);

    int deleteByExample(TbPurchaseExample example);

    int insertSelective(TbPurchase record);

    int updateByExampleSelective(@Param("record") TbPurchase record, @Param("example") TbPurchaseExample example);
}