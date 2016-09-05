package com.xmomen.module.wx.service;


import com.alibaba.fastjson.JSON;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.wx.constants.ResponseMsgTypeEnum;
import com.xmomen.module.wx.model.MessageBase;
import com.xmomen.module.wx.model.requestModel.message.RequestMessageText;
import com.xmomen.module.wx.model.responseModel.ResponseMessageText;
import com.xmomen.module.wx.util.MessageUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 微信文本消息响应类
 */
@Service
public class ResponseMessageTextService implements ResponseMessageBaseService {

	@Autowired
	BindService bindService;
    Logger log = LoggerFactory.getLogger(ResponseMessageTextService.class);

    /**
     * 对微信的请求消息做出响应
     *
     * @param requestMessage    请求的消息内容
     * @param requestMessageExt 请求内容封装类
     * @return 回复给微信的消息 （xml格式）
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public String handleRequestMessage(MessageBase requestMessage) throws InstantiationException, IllegalAccessException {
        ResponseMessageText responseMessageText = new ResponseMessageText();
        //发送者
        responseMessageText.setFromUserName(requestMessage.getToUserName());
        //接收者
        responseMessageText.setToUserName(requestMessage.getFromUserName());
        //回复时间
        responseMessageText.setCreateTime(System.currentTimeMillis());
        //回复类型
        responseMessageText.setMsgType(ResponseMsgTypeEnum.text.toString().toLowerCase());
        //回复默认内容
        String requestContent = ((RequestMessageText)requestMessage).getContent();
        // /文本消息请求
        log.info("获取的内容"+requestContent);
        if(requestContent.startsWith("绑定")){
			if(requestMessage instanceof RequestMessageText) {
				String phone = requestContent.substring(2);
				log.info("openId:"+requestMessage.getFromUserName()+"，绑定的手机号为:" + phone);
					boolean flag = bindService.bindAccount(requestMessage.getFromUserName(), phone, "2");
					if(flag){
						responseMessageText.setContent("绑定成功!");
					}else{
						responseMessageText.setContent("号码:"+phone+"不存在");
					}
			} else {
				responseMessageText.setContent("绑定格式为：绑定+手机号");
			}
		}else{
			responseMessageText.setContent("发送固定格式信息进行绑定！绑定格式：绑定+手机号(绑定131XXXXXXXX)");
		}
        return MessageUtils.convertEntityToXml((ResponseMessageText) responseMessageText);
    }
}
