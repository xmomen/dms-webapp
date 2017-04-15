package com.xmomen.module.wx.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.wx.constants.AuthorizeScope;
import com.xmomen.module.wx.constants.WechatUrlConstants;
import com.xmomen.module.wx.model.AccessTokenOAuth;

public class Auth2Handler {

    static Logger logger = LoggerFactory.getLogger(Auth2Handler.class);

    private static final String STATE = "WJHYORDER";

    private static final String APPID = "wx7e9cfeede085ae15";
    private static final String APPSECRET = "0ed640e24adc6a0e7b89c865c4271f74";


    public static String getOauthUrl(String redirectUrl) {
        String url = "";
        try {
            url = WechatUrlConstants.OAUTH.replace("APPID", APPID)
                    .replace("REDIRECT_URI", URLEncoder.encode(redirectUrl, "UTF-8"))
                    .replace("SCOPE", AuthorizeScope.snsapi_userinfo.toString())
                    .replace("STATE", STATE);
        } catch (UnsupportedEncodingException e) {
            logger.error("获取oauthURL失败，可能是redirectUrl进行urlencoder时出错，请检查此参数：" + redirectUrl, e);
            e.printStackTrace();
        }
        return url;
    }

    /**
     * @param code
     * @return
     */
    public static AccessTokenOAuth getAccessToken(String code) {
        String url = WechatUrlConstants.GET_ACCESS_TOKEN_OAUTH.replace("APPID", APPID)
                .replace("SECRET", APPSECRET)
                .replace("CODE", code);
        logger.info("auth openid url --->" + url);
        HttpRequest shc = new HttpClient();
        String result = shc.doPost(url, "");
        logger.info("request result -->" + result);
        AccessTokenOAuth accessToken = null;
        if (StringUtilsExt.isNotEmpty(result)) {
            JSONObject json = JSON.parseObject(result);
            if (null != json) {
                if (StringUtilsExt.isNotEmpty(json.getString("errcode")) && json.getIntValue("errcode") != 0) {
                    logger.error("oauth 获取access_token失败,code=" + json.getIntValue("errcode") + ",msg=" + json.getIntValue("errmsg"));
                }
                else {
                    accessToken = new AccessTokenOAuth();
                    accessToken.setAccessToken(json.getString("access_token"));
                    accessToken.setExpiresIn(json.getIntValue("expires_in"));
                    accessToken.setRefreshToken(json.getString("refresh_token"));
                    accessToken.setOpenid(json.getString("openid"));
                    accessToken.setScope(json.getString("scope"));
                }
            }
        }
        return accessToken;
    }
}
