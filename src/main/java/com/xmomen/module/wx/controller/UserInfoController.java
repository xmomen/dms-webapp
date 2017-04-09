package com.xmomen.module.wx.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.mapper.MemberMapper;
import com.xmomen.module.base.model.MemberModel;
import com.xmomen.module.wx.model.AjaxResult;
import com.xmomen.module.wx.model.UserInfoModel;
import com.xmomen.module.wx.model.WeixinUserInfo;
import com.xmomen.module.wx.service.WeixinApiService;

@RestController
public class UserInfoController {

	@Autowired
	WeixinApiService weixinApiService;
	
	@Autowired
    MybatisDao mybatisDao;
	
	@RequestMapping(value = "/wx/userInfo",  method = RequestMethod.GET)
	public AjaxResult getUserInfo(@RequestParam(value = "openId", required = false) String openId,
			@RequestParam(value = "memberId", required = false) Integer memberId) {
		AjaxResult ajaxResult = new AjaxResult();
		UserInfoModel userInfo = null;
		ajaxResult.setResult(1);
		if(!StringUtils.isEmpty(openId)) {
			String publicUid = "gh_67c2b712d650";
			String accessToken = weixinApiService.getAccessToken(publicUid);
			WeixinUserInfo weixinUserInfo = WeixinApiService.getWeixinUserInfo(accessToken, openId);
			if(weixinUserInfo != null && !StringUtils.isEmpty(weixinUserInfo.getNickname())) {
				userInfo = new UserInfoModel();
				userInfo.setName(weixinUserInfo.getNickname());
				userInfo.setHeadimgurl(weixinUserInfo.getHeadimgurl());
			}
			
		}
		if(memberId != null) {
			Map<String, Object> map = new HashMap<String,Object>();
	        map.put("id", memberId);
	        MemberModel memberModel = mybatisDao.getSqlSessionTemplate().selectOne(MemberMapper.MemberMapperNameSpace + "getMemberList", map);
		    if(memberModel != null) {
		    	userInfo = new UserInfoModel();
		    	userInfo.setName(memberModel.getName());
		    }
		}
		ajaxResult.setContent(userInfo);
		return ajaxResult;
	}
}
