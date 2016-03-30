package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdManagerCompany;
import com.xmomen.module.base.entity.CdManagerCompanyExample;
import org.apache.ibatis.annotations.Param;

public interface CdManagerCompanyMapper extends MybatisMapper {
    int countByExample(CdManagerCompanyExample example);

    int deleteByExample(CdManagerCompanyExample example);

    int insertSelective(CdManagerCompany record);

    int updateByExampleSelective(@Param("record") CdManagerCompany record, @Param("example") CdManagerCompanyExample example);
}