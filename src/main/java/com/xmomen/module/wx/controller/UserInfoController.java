package com.xmomen.module.wx.controller;

import java.util.HashMap;
import java.util.Map;

import com.xmomen.module.wx.util.Auth2Handler;
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

    @RequestMapping(value = "/wx/userInfo", method = RequestMethod.GET)
    public AjaxResult getUserInfo(@RequestParam(value = "openId", required = false) String openId,
                                  @RequestParam(value = "accessToken", required = false) String accessToken,
                                  @RequestParam(value = "memberId", required = false) Integer memberId) {
        AjaxResult ajaxResult = new AjaxResult();
        UserInfoModel userInfo = new UserInfoModel();
        ajaxResult.setResult(1);
        if (!StringUtils.isEmpty(openId)) {
            String publicUid = "gh_9248df680cef";
            String accessTokenWechat = weixinApiService.getAccessToken(publicUid);
            //获取微信关注的用户信息
            WeixinUserInfo weixinUserInfo = WeixinApiService.getWeixinUserInfo(accessTokenWechat, openId);

            //未关注微信
            if (weixinUserInfo.getSubscribe() == 0) {
                //获取网页授权的微信信息
                weixinUserInfo = Auth2Handler.getNoGuanzhuWeixinUserInfo(accessToken, openId);
            }

            if (weixinUserInfo != null && !StringUtils.isEmpty(weixinUserInfo.getNickname())) {
                userInfo.setName(weixinUserInfo.getNickname());
                userInfo.setHeadimgurl(weixinUserInfo.getHeadimgurl());
            }

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", memberId);
            MemberModel memberModel = mybatisDao.getSqlSessionTemplate().selectOne(MemberMapper.MemberMapperNameSpace + "getMemberList", map);
            if (memberModel != null) {
                userInfo.setName(memberModel.getName());
                userInfo.setPhone(memberModel.getPhoneNumber());
            }
        }
        ajaxResult.setContent(userInfo);
        return ajaxResult;
    }
}
