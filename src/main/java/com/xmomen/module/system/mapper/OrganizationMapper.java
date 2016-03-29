package com.xmomen.module.system.mapper;

import com.xmomen.module.system.model.OrganizationModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Jeng on 16/3/27.
 */
public interface OrganizationMapper {

    @Select(value = "SELECT s.ID,s.NAME,s.DESCRIPTION, s.PARENT_ID,p.NAME AS parent_name FROM sys_organization s LEFT JOIN sys_organization p ON p.ID=s.PARENT_ID where FIND_IN_SET(s.id, query_children_organization(${id}))")
    @ResultType(OrganizationModel.class)
    public List<OrganizationModel> getOrganizationTree(@Param(value = "id") Integer id);
}
