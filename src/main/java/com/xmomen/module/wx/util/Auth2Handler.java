package com.xmomen.module.wx.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.xmomen.module.wx.model.WeixinUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
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

    private static final String APPID = "wx6e25cbce77bacdff";
    private static final String APPSECRET = "df1d3165ce5ac50dd355f66369a6503e";

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

    /**
     * 获取未关注公众号用户信息
     *
     * @param accessToken 访问令牌
     * @param openid      OpenID
     * @return 信息用户信息
     */
    public static WeixinUserInfo getNoGuanzhuWeixinUserInfo(String accessToken, String openid) {
        try {
            String url = WechatUrlConstants.GET_NO_GUANZHU_USER_INFO.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openid);

            String result = HttpUtils.doGet(url);
            WeixinUserInfo weixinUserInfo = JSON.parseObject(result, WeixinUserInfo.class);
            if (StringUtils.isNotEmpty(weixinUserInfo.getErrcode())) {
                logger.info("获取用户信息出错，错误码：" + weixinUserInfo.getErrcode());
                return null;
            }
            logger.info("获取用户信息结果字符串：" + result);
            return weixinUserInfo;
        } catch (Exception e) {
            logger.error("获取用户信息失败：", e);
        }
        return null;
    }
}
