package com.xmomen.module.order.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.order.entity.TbPackingRecord;
import com.xmomen.module.order.entity.TbPackingRecordExample;
import org.apache.ibatis.annotations.Param;

public interface TbPackingRecordMapper extends MybatisMapper {
    int countByExample(TbPackingRecordExample example);

    int deleteByExample(TbPackingRecordExample example);

    int insertSelective(TbPackingRecord record);

    int updateByExampleSelective(@Param("record") TbPackingRecord record, @Param("example") TbPackingRecordExample example);
}