package com.xmomen.module.wx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.wx.entity.WxAppSetting;

@Service
public class AppSettingService {
	@Autowired
	MybatisDao mybatisDao;
	
	public WxAppSetting getAppSetting(String publicUid){
		WxAppSetting appSetting = new WxAppSetting();
		appSetting.setPublicUid(publicUid);
		appSetting = mybatisDao.selectOneByModel(appSetting);
		return appSetting;
	}
}
