package com.xmomen.module.wx.pay.service;

import com.xmomen.module.wx.pay.common.Configure;
import com.xmomen.module.wx.pay.model.PayReqData;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 16:03
 */
public class PayService extends BaseService {

    public PayService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super(Configure.PUBLIC_PAY);
    }

    /**
     * 请求支付服务
     *
     * @param payReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public String request(PayReqData payReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(payReqData);

        return responseString;
    }
}
