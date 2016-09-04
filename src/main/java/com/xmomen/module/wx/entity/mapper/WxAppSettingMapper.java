package com.xmomen.module.wx.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.wx.entity.WxAppSetting;
import com.xmomen.module.wx.entity.WxAppSettingExample;
import org.apache.ibatis.annotations.Param;

public interface WxAppSettingMapper extends MybatisMapper {
    int countByExample(WxAppSettingExample example);

    int deleteByExample(WxAppSettingExample example);

    int insertSelective(WxAppSetting record);

    int updateByExampleSelective(@Param("record") WxAppSetting record, @Param("example") WxAppSettingExample example);
}