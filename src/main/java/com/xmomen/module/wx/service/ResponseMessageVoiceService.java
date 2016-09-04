package com.xmomen.module.wx.service;


import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.wx.model.MessageBase;
import com.xmomen.module.wx.model.requestModel.message.RequestMessageVoice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 微信语音消息响应类
 */
@Service
public class ResponseMessageVoiceService implements ResponseMessageBaseService {

    Logger log = LoggerFactory.getLogger(ResponseMessageVoiceService.class);

    /**
     * 对微信的语音请求消息做出响应
     *
     * @param requestMessage    请求的消息内容
     * @param requestMessageExt 请求内容封装类
     * @return 回复给微信的消息 （xml格式）
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public String handleRequestMessage(MessageBase requestMessage) throws InstantiationException, IllegalAccessException {
        return StringUtilsExt.EMPTY;
    }
}
