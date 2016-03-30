package com.xmomen.module.base.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.base.entity.CdCompany;
import com.xmomen.module.base.entity.CdCompanyExample;
import org.apache.ibatis.annotations.Param;

public interface CdCompanyMapper extends MybatisMapper {
    int countByExample(CdCompanyExample example);

    int deleteByExample(CdCompanyExample example);

    int insertSelective(CdCompany record);

    int updateByExampleSelective(@Param("record") CdCompany record, @Param("example") CdCompanyExample example);
}