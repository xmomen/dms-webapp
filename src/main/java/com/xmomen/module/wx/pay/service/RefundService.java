package com.xmomen.module.wx.pay.service;

import com.xmomen.module.wx.pay.common.Configure;
import com.xmomen.module.wx.pay.model.RefundReqData;

/**
 * 商家退款
 * @author xiao
 *
 */
public class RefundService extends BaseService {

	public RefundService() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		super(Configure.APPLY_REFUND);
	}

	/**
     * 申请退款服务
     *
     * @param payReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public String request(RefundReqData refundReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(refundReqData);

        return responseString;
    }
}
