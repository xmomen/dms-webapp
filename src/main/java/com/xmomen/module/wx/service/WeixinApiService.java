package com.xmomen.module.wx.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.wx.constants.WeixinConsts;
import com.xmomen.module.wx.entity.WxAppSetting;
import com.xmomen.module.wx.model.AccessToken;
import com.xmomen.module.wx.model.AccessTokenOAuth;
import com.xmomen.module.wx.model.JsApiTicket;
import com.xmomen.module.wx.model.WeixinUserInfo;
import com.xmomen.module.wx.util.DateUtils;
import com.xmomen.module.wx.util.HttpUtils;
import com.xmomen.module.wx.util.JsonUtils;

import com.xmomen.module.wx.util.SignUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 微信认证处理类
 */
@Component
public class WeixinApiService {
    static Logger log = LoggerFactory.getLogger(WeixinApiService.class);

    @Autowired
    AppSettingService appSettingService;
    
    @Autowired
    MybatisDao mybatisDao;

    /**
     * 取得微信用户信息
     *
     * @param accessToken 访问令牌
     * @param openid      OpenID
     * @return 信息用户信息
     */
    public static WeixinUserInfo getWeixinUserInfo(String accessToken, String openid) {
        try {
            String url = WeixinConsts.GET_WEIXIN_USER_INFO.replace("{ACCESS_TOKEN}", accessToken).replace("{OPENID}", openid);

            String result = HttpUtils.doGet(url);

            log.info("获取用户信息结果字符串：" + result);

            if (StringUtilsExt.isNotEmpty(result)) {
                return JsonUtils.parseJSON(StringEscapeUtils.unescapeJson(result), WeixinUserInfo.class);
            }
        } catch (Exception e) {
            log.error("获取用户信息失败：", e);
        }
        return null;
    }

    /**
     * 获得accessToken
     *
     * @return
     */
    public String getAccessToken(String publicUid) {
        //获取公众号的配置
        WxAppSetting appSettingExt = appSettingService.getAppSetting(publicUid);

        if (appSettingExt == null) {
            return StringUtils.EMPTY;
        }
        //获取缓存的access_token
        AccessToken accessToken = new AccessToken(appSettingExt.getAccessToken(), String.valueOf(appSettingExt.getExpiresIn()), appSettingExt.getLastGetTime());

        //判断是否access_token是否过期
        if (accessToken.getAccess_token() == null || accessToken.isExpired()) {
            //过期重新获取accessToken
            String url = WeixinConsts.GET_ACCESS_TOKEN_URL.replace("{APPID}", appSettingExt.getAppId()).replace("{APPSECRET}", appSettingExt.getAppSecret());

            //get请求微信服务器获取到accessToken
            String result = HttpUtils.doGet(url);
            if (!StringUtils.isEmpty(result)) {
                try {
                    accessToken = JsonUtils.parseJSON(StringEscapeUtils.unescapeJson(result), AccessToken.class);

                    log.info("最新的accessToken:" + accessToken.getAccess_token());

                    appSettingExt.setAccessToken(accessToken.getAccess_token());
                    appSettingExt.setExpiresIn(Integer.parseInt(accessToken.getExpires_in()));
                    appSettingExt.setLastGetTime(DateUtils.getNowDate());

                    mybatisDao.update(appSettingExt);

                    return accessToken.getAccess_token();
                } catch (Exception e) {
                    log.error("AccessToken获得异常：", e);
                }
            }
            return StringUtilsExt.EMPTY;
        }
        //未过期
        else {
            return accessToken.getAccess_token();
        }
    }

    /**
     * 获取网页授权的access_token
     *
     * @param code
     * @return
     */
    public AccessTokenOAuth getAccessToken(String code, String publicUid) {
        //获取微信配置信息
    	 WxAppSetting appSettingExt = appSettingService.getAppSetting(publicUid);
        AccessTokenOAuth accessToken = null;
        try {
            //获取网页授权URL拼装
            String url = WeixinConsts.GET_ACCESS_TOKEN_OAUTH.replace("{APPID}", appSettingExt.getAppId()).replace("{SECRET}", appSettingExt.getAppSecret()).replace("{CODE}", code);
            log.info("授权URL：" + url);

            //get请求微信服务器
            String result = HttpUtils.doGet(url);
            log.info("授权结果：" + result);

            //反馈信息存在
            if (StringUtilsExt.isNotEmpty(result)) {
                accessToken = JsonUtils.parseJSON(StringEscapeUtils.unescapeJson(result), AccessTokenOAuth.class);
            }
        } catch (Exception e) {
            log.error("获取accessToken失败", e);
        }
        return accessToken;
    }

    /**
     * 获取jsapi-ticket
     * @param publicUid
     * @return JsApiTicket
     */
    public JsApiTicket getJsApiTicket(String publicUid){
        String accessToken = getAccessToken(publicUid);
        String url = WeixinConsts.JS_API_TICKET.replace("{ACCESS_TOKEN}", accessToken);
        JsApiTicket jsApiTicket = null;
        //get请求微信服务器
        String result = HttpUtils.doGet(url);
        log.info("微信[js_api_ticket]请求结果：" + result);
        if (StringUtils.isNotEmpty(result)) {
            jsApiTicket = JsonUtils.parseJSON(StringEscapeUtils.unescapeJson(result), JsApiTicket.class);
        }
        return jsApiTicket;
    }

    /**
     * 获取jsapi-sdk签名信息
     * @param publicUid
     * @param url
     * @return
     */
    public Map getJsSDKConfig(String publicUid, String url){
        getAccessToken(publicUid);
        JsApiTicket jsApiTicket = getJsApiTicket(publicUid);
        Map map = SignUtil.sign(jsApiTicket.getTicket(), url);
        return map;
    }
}
