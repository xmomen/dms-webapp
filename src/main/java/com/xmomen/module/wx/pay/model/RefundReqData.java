package com.xmomen.module.wx.pay.model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.xmomen.module.wx.pay.common.Configure;
import com.xmomen.module.wx.pay.common.RandomStringGenerator;
import com.xmomen.module.wx.pay.common.Signature;

public class RefundReqData {

	//小程序id 必填
    private String appid = "";
    //商户号 必填
    private String mch_id = "";
	//private String device_info = "";
    //随机字符串 必填
	private String nonce_str = "";
	//签名 必填
	private String sign = "";
	//private String sign_type = "";
	//微信订单号 与 商户订单号 二选一必填 
	//微信订单号 
	private String transaction_id = "";
	//商户订单号
	private String out_trade_no = "";
	// 商户退款单号 必填
	private String out_refund_no = "";
	
	//订单金额 必填
	private int total_fee = 0;
	//退款金额 必填
	private int refund_fee = 0;
	//操作员 必填 默认为商户号
	private String op_user_id = "";
	
	//退款资金来源 否
	//private String refund_account = "";
			
	public RefundReqData(String transactionId, int totalFee, int refundFee, String outRefundNo) {
		//微信分配的公众号ID（开通公众号之后可以获取到）
        setAppid(Configure.getAppid());

        //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
        setMch_id(Configure.getMchid());
        
        setTransaction_id(transactionId);
        
        setTotal_fee(totalFee);
        
        setRefund_fee(refundFee);
        
        setOut_refund_no(outRefundNo);
        
        setOp_user_id(Configure.getMchid());
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

	/*public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}*/

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

	/*public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}*/

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public int getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(int refund_fee) {
		this.refund_fee = refund_fee;
	}

	public String getOp_user_id() {
		return op_user_id;
	}

	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}

	/*public String getRefund_account() {
		return refund_account;
	}

	public void setRefund_account(String refund_account) {
		this.refund_account = refund_account;
	}*/
	
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
