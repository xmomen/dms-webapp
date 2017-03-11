package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdAttachment;
import com.xmomen.module.base.entity.CdAttachmentExample;
import org.apache.ibatis.annotations.Param;

public interface CdAttachmentMapper extends MybatisMapper {
    int countByExample(CdAttachmentExample example);

    int deleteByExample(CdAttachmentExample example);

    int insertSelective(CdAttachment record);

    int updateByExampleSelective(@Param("record") CdAttachment record, @Param("example") CdAttachmentExample example);
}