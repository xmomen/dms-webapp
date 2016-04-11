package com.xmomen.module.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.xmomen.module.base.model.ItemCategoryModel;

/**
 * Created by Jeng on 16/3/27.
 */
public interface ItemCategoryMapper {

    @Select(value = "SELECT s.ID,s.CATEGORY_NAME AS name, s.PARENT_ID,p.CATEGORY_NAME AS parent_name FROM cd_category s LEFT JOIN cd_category p ON p.ID=s.PARENT_ID where FIND_IN_SET(s.id, query_children_category(${id}))")
    @ResultType(ItemCategoryModel.class)
    public List<ItemCategoryModel> getItemCategoryTree(@Param(value = "id") Integer id);
}
