package com.xmomen.module.wx.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.wx.constants.WeixinConsts;
import com.xmomen.module.wx.entity.WxAppSetting;
import com.xmomen.module.wx.model.AccessToken;
import com.xmomen.module.wx.model.AccessTokenOAuth;
import com.xmomen.module.wx.model.JsApiTicket;
import com.xmomen.module.wx.model.PayAttachModel;
import com.xmomen.module.wx.model.WeixinUserInfo;
import com.xmomen.module.wx.module.order.service.MyOrderService;
import com.xmomen.module.wx.pay.common.Configure;
import com.xmomen.module.wx.pay.common.MD5;
import com.xmomen.module.wx.pay.common.RandomStringGenerator;
import com.xmomen.module.wx.pay.common.Util;
import com.xmomen.module.wx.pay.entity.TbPayRecord;
import com.xmomen.module.wx.pay.model.PayReqData;
import com.xmomen.module.wx.pay.model.PayResData;
import com.xmomen.module.wx.pay.model.RefundReqData;
import com.xmomen.module.wx.pay.model.RefundResData;
import com.xmomen.module.wx.pay.model.WeixinPayRecord;
import com.xmomen.module.wx.pay.service.PayRecordService;
import com.xmomen.module.wx.pay.service.PayService;
import com.xmomen.module.wx.pay.service.RefundService;
import com.xmomen.module.wx.util.DateUtils;
import com.xmomen.module.wx.util.HttpUtils;
import com.xmomen.module.wx.util.JsonUtils;
import com.xmomen.module.wx.util.SignUtil;

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

    @Autowired
    MyOrderService myOrderService;

    @Autowired
    PayRecordService payRecordService;

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
        //获取公众号的配置
        WxAppSetting appSettingExt = appSettingService.getAppSetting(publicUid);
        map.put("appId", appSettingExt.getAppId());
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
                try {
                    if (StringUtils.equals("SUCCESS", payResData.getResult_code())) {
                        synchronized (this) {
                            //考虑微信的重复通知的可能性，所以加锁控制
                            myOrderService.payCallBack(payResData);
                        }
                    }
                    else {
                        log.error("微信支付失败:" + payResData.getErr_code_des());
                        return returnFail();
                    }
                } catch (Exception e) {
                	e.printStackTrace();
                    log.error("业务逻辑处理失败:" + payResData);
                    return returnFail();
                }
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
     * @param totalFee   总金额（元）
     * @param request
     * @return
     */
    public PayResData payOrder(String outTradeNo, Double totalFee, String openId, Integer type, HttpServletRequest request) {
        PayAttachModel attachModel = null;
        String tradeId = UUID.randomUUID().toString().replaceAll("-", "");
        if (!type.equals(2) && !type.equals(1)) {
            log.info("不合法的交易类型：" + type + ",合法的值为[1, 2]");
            return null;
        }

        log.info("outTradeNo:" + outTradeNo + ",totalFee:" + totalFee + ",openId:" + openId + ",type:" + type + ",request:" + request.toString());
        attachModel = new PayAttachModel(type, outTradeNo, tradeId);
        String attachement = JSON.toJSONString(attachModel);
        totalFee = totalFee * 100;
        PayReqData payReqData = new PayReqData("订单付费", tradeId, 1, getIp2(request), openId, attachement);

        try {
            String result = new PayService().request(payReqData);
            log.info("请求返回的结果：" + result);

            //将从API返回的XML数据映射到Java对象
            PayResData payResData = (PayResData) Util.getObjectFromXML(result, PayResData.class);
            //统一下单失败
            if (payResData.getReturn_code().equals("FAIL")) {
                log.info("统一下单失败：" + payResData.getReturn_msg());
                return null;
            }
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
            payResData.setAppid(Configure.getAppid());
            payResData.setMch_id(null);


            //插入支付记录到tb_pay_record表
            WeixinPayRecord weixinPayRecord = new WeixinPayRecord();
            weixinPayRecord.setTradeNo(outTradeNo);
            weixinPayRecord.setTradeId(tradeId);
            weixinPayRecord.setOpenId(openId);
            weixinPayRecord.setTradeType(type);
            weixinPayRecord.setTotalFee(totalFee.intValue());
            try {
                payRecordService.addPayRecord(weixinPayRecord);
            } catch (Exception e) {
                log.error("记录支付失败：" + weixinPayRecord);
            }

            return payResData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public RefundResData refund(String tradeNo, int refundFee) {
    	TbPayRecord queryModel = new TbPayRecord();
    	queryModel.setTradeNo(tradeNo);
    	queryModel.setTradeType(1);
    	queryModel.setCompleteTime(new Date());//交易完成时间不为空
    	TbPayRecord tbPayRecord = payRecordService.getTbpayRecordByRecord(queryModel);
    	if(tbPayRecord == null) {
    		log.error("支付记录不存在或者支付未完成：" + tradeNo);
    		throw new IllegalArgumentException("支付记录不存在");
    	}
    	String transactionId = tbPayRecord.getTransactionId();
    	if(StringUtils.isEmpty(transactionId)) {
    		throw new IllegalArgumentException("未找到订单" + tradeNo + "关联的微信支付订单号" );
    	}
    	TbPayRecord refundPayRecordQuery = new TbPayRecord();
    	refundPayRecordQuery.setTradeType(3);
    	refundPayRecordQuery.setTransactionId(transactionId);
    	List<TbPayRecord> refundRecords = payRecordService.getTbpayRecordListByRecord(tbPayRecord);
    	if(!refundRecords.isEmpty()) {
    		//这里限定一个订单只能退款一次
    		throw new IllegalArgumentException("该订单已经提交过退款请求");
    	}
    	String outRefundNo = UUID.randomUUID().toString().replaceAll("-", "");
    	int totalFee = tbPayRecord.getTotalFee().multiply(new BigDecimal(100)).intValue();
    	RefundReqData refundReqData = new RefundReqData(transactionId, totalFee, refundFee, outRefundNo);
    	try {
    		String result = new RefundService().request(refundReqData);
    		log.info("请求返回的结果：" + result);
    		//将从API返回的XML数据映射到Java对象
    		RefundResData refundResData = (RefundResData) Util.getObjectFromXML(result, RefundResData.class);
    		if (StringUtils.equals("SUCCESS", refundResData.getReturn_code()) && 
    				StringUtils.equals("SUCCESS",refundResData.getResult_code())) {
    				// 申请退款成功，设置completeTime
    				TbPayRecord refundRecord = new TbPayRecord();
    				refundRecord.setId(outRefundNo);
    				refundRecord.setOpenId(tbPayRecord.getOpenId());
    				refundRecord.setTradeType(3);//退款类型
    				refundRecord.setTransactionId(tbPayRecord.getTransactionId());
    				refundRecord.setTransactionTime(tbPayRecord.getTransactionTime());
    				refundRecord.setCompleteTime(new Date());
    				refundRecord.setTotalFee(new BigDecimal(Double.valueOf(refundFee)%100));
    				refundRecord.setTradeNo(tbPayRecord.getTradeNo());
    				payRecordService.insert(tbPayRecord);
    		} else {
    			return null;
    		}
    		return refundResData;
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
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        return ip;
    }
}
