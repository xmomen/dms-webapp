package com.xmomen.module.system.mapper;

import com.xmomen.module.system.model.Dictionary;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Jeng on 16/4/2.
 */
public interface DictionaryMapper {

    @Select(value = "")
    @ResultType(Dictionary.class)
    public Dictionary getDictionaryList(@Param(value = "keyword") String keyword);
}
