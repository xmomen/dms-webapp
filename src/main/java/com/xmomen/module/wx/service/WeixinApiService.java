package com.xmomen.module.wx.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.wx.constants.WeixinConsts;
import com.xmomen.module.wx.entity.WxAppSetting;
import com.xmomen.module.wx.model.AccessToken;
import com.xmomen.module.wx.model.AccessTokenOAuth;
import com.xmomen.module.wx.model.JsApiTicket;
import com.xmomen.module.wx.model.WeixinUserInfo;
import com.xmomen.module.wx.pay.common.Configure;
import com.xmomen.module.wx.pay.common.MD5;
import com.xmomen.module.wx.pay.common.RandomStringGenerator;
import com.xmomen.module.wx.pay.common.Util;
import com.xmomen.module.wx.pay.model.PayReqData;
import com.xmomen.module.wx.pay.model.PayResData;
import com.xmomen.module.wx.pay.service.PayService;
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
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
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
     *
     * @param publicUid
     * @return JsApiTicket
     */
    public JsApiTicket getJsApiTicket(String publicUid) {
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
     *
     * @param publicUid
     * @param url
     * @return
     */
    public Map getJsSDKConfig(String publicUid, String url) {
        getAccessToken(publicUid);
        JsApiTicket jsApiTicket = getJsApiTicket(publicUid);
        Map map = SignUtil.sign(jsApiTicket.getTicket(), url);
        return map;
    }

    /**
     * 处理回调函数
     *
     * @param notityXml
     * @return
     */
    @Transactional
    public String notify(String notityXml) {
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(notityXml)) {
            //将从API返回的XML数据映射到Java对象
            PayResData payResData = (PayResData) Util.getObjectFromXML(notityXml, PayResData.class);
            log.info("returnCode:" + payResData.getResult_code());
            log.info("return_code：" + payResData.getReturn_code());
            log.info("out_trade_no:" + payResData.getOut_trade_no());
            if (StringUtils.equals("SUCCESS", payResData.getReturn_code())) {
                //进行业务处理
            }
            else {
                return returnFail();
            }
            return returnSussess();
        }
        else {
            log.info("回调处理失败");
            return returnFail();
        }
    }

    private String returnSussess() {
        return "<xml><return_code><![CDATA[SUCCESS]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }

    private String returnFail() {
        return "<xml><return_code><![CDATA[FAIL]></return_code><return_msg><![CDATA[FAIL]]></return_msg></xml>";
    }


    /**
     * 支付
     *
     * @param outTradeNo 订单号
     * @param totalFee   总金额（分）
     * @param request
     * @return
     */
    public PayResData payOrder(String outTradeNo, Integer totalFee, String openId, HttpServletRequest request) {
        PayReqData payReqData = new PayReqData("订单付费", outTradeNo, totalFee, getIp2(request), openId, "");

        try {
            String result = new PayService().request(payReqData);
            log.info("请求返回的结果：" + result);

            //将从API返回的XML数据映射到Java对象
            PayResData payResData = (PayResData) Util.getObjectFromXML(result, PayResData.class);
            //再次签名
            long timeStamp = System.currentTimeMillis() / 1000;
            String nonceStr = RandomStringGenerator.getRandomStringByLength(32);
            String packageStr = "prepay_id=" + payResData.getPrepay_id();
            //待签名字符串
            //根据API给的签名规则进行签名 appId,nonceStr,package,signType,timeStamp
            String signStr = "appId=" + Configure.getAppid() + "&nonceStr=" + nonceStr + "&package=" + packageStr + "&signType=MD5&timeStamp=" + timeStamp + "&key=" + Configure.getKey();
            log.info("Sign Before MD5：" + signStr);
            String sign = MD5.MD5Encode(signStr).toUpperCase();

            log.info("SIGN签名：" + sign);

            payResData.setSign(sign);
            payResData.setTimeStamp(String.valueOf(timeStamp));
            payResData.setNonce_str(nonceStr);
            payResData.setPackageStr(packageStr);
            payResData.setAppid(null);
            payResData.setMch_id(null);
            return payResData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取客户端IP
     *
     * @param request
     * @return
     */
    public static String getIp2(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            }
            else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
}
