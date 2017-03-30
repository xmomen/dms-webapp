package com.xmomen.module.wx.pay.model;

/**
 * User: rizenguo
 * Date: 2014/10/22
 * Time: 21:29
 */

import com.xmomen.module.wx.pay.common.Configure;
import com.xmomen.module.wx.pay.common.RandomStringGenerator;
import com.xmomen.module.wx.pay.common.Signature;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求支付API需要提交的数据
 */
public class PayReqData {

    //小程序id 必填
    private String appid = "";
    //商户号 必填
    private String mch_id = "";
    //设备号 否
//    private String device_info = "";
    //随机字符串 必填
    private String nonce_str = "";
    //签名 必填
    private String sign = "";
    //签名类型 否
//    private String sign_type = "";
    //商品描述 是
    private String body = "";
    //商品详情 否
//    private String detail = "";
    //附件数据 否
    private String attach;
    //商品订单号 是
    private String out_trade_no = "";
    //货币类型
    private String fee_type;
    //总费用 是 到分
    private int total_fee = 0;
    //终端Ip
    private String spbill_create_ip = "";
    //交易起始时间
//    private String time_start = "";
    //交易结束时间
//    private String time_expire = "";
    //商品标记
//    private String goods_tag = "";
    //通知地址 是
    private String notify_url = "";
    //交易类型 是
    private String trade_type = "JSAPI";
    //用户标识 是
    private String openid = "";

    /**
     * @param body
     * @param outTradeNo
     * @param totalFee
     * @param spBillCreateIP
     * @param openId
     */
    public PayReqData(String body, String outTradeNo, int totalFee, String spBillCreateIP, String openId, String attach) {

        //微信分配的公众号ID（开通公众号之后可以获取到）
        setAppid(Configure.getAppid());

        //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
        setMch_id(Configure.getMchid());

        //要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
        setBody(body);

        //商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
        setOut_trade_no(outTradeNo);

        //附加数据
        setAttach(attach);

        //订单总金额，单位为“分”，只能整数
        setTotal_fee(totalFee);

        //通知地址
        setNotify_url(Configure.getNotify_url());

        //订单生成的机器IP
        setSpbill_create_ip(spBillCreateIP);

        //商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
        setOpenid(openId);

        //随机字符串，不长于32 位
        setNonce_str(RandomStringGenerator.getRandomStringByLength(32));

        //根据API给的签名规则进行签名
        String sign = Signature.getSign(toMap());

        setSign(sign);//把签名数据设置到Sign这个属性中
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object obj;
            try {
                obj = field.get(this);
                if (obj != null && !obj.equals("")) {
                    map.put(field.getName(), obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
