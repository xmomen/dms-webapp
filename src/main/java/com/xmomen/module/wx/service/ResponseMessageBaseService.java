package com.xmomen.module.wx.service;

import com.xmomen.module.wx.model.MessageBase;

import com.xmomen.module.wx.model.MessageBase;

/**
 * 微信服务响应公共接口类
 */
public interface ResponseMessageBaseService {
    /**
     * 对微信的请求消息做出响应
     *
     * @param requestMessage    请求的消息内容
     * @param requestMessageExt 请求内容封装类
     * @return 回复给微信的消息 （xml格式）
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public String handleRequestMessage(MessageBase requestMessage) throws InstantiationException, IllegalAccessException, Exception;
}
