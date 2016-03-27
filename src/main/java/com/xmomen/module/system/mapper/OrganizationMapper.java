package com.xmomen.module.system.mapper;

import com.xmomen.module.system.entity.SysOrganization;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Jeng on 16/3/27.
 */
public interface OrganizationMapper {

    @Select(value = "select * from sys_organization s where FIND_IN_SET(s.id, query_children_organization(${id}))")
    @ResultType(SysOrganization.class)
    public List<SysOrganization> getOrganizationTree(@Param(value = "id") Integer id);
}
