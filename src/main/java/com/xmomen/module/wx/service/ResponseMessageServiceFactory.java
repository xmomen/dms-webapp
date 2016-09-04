package com.xmomen.module.wx.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xmomen.module.wx.constants.RequestMsgTypeEnum;


/**
 * 微信服务响应处理工厂类
 */
@Component
public class ResponseMessageServiceFactory {
    static Logger log = LoggerFactory.getLogger(ResponseMessageServiceFactory.class);

    @Autowired
    ResponseMessageEventService responseMessageEventService;

    @Autowired
    ResponseMessageTextService responseMessageTextService;

    @Autowired
    ResponseMessageImageService responseMessageImageService;

    @Autowired
    ResponseMessageVoiceService responseMessageVoiceService;

    @Autowired
    ResponseMessageVideoService responseMessageVideoService;

    public ResponseMessageBaseService createInstance(String key) throws InstantiationException, IllegalAccessException {
        //事件消息响应
        if (key.equals(RequestMsgTypeEnum.event.toString())) {
            return responseMessageEventService;
        }
        //文本消息响应
        else if (key.equals(RequestMsgTypeEnum.text.toString())) {
            return responseMessageTextService;
        }
        //图片消息响应
        else if (key.equals(RequestMsgTypeEnum.image.toString())) {
            return responseMessageImageService;
        }
        //语音消息响应
        else if (key.equals(RequestMsgTypeEnum.voice.toString())) {
            return responseMessageVoiceService;
        }
        //视频消息响应
        else if (key.equals(RequestMsgTypeEnum.video.toString()) || key.equals(RequestMsgTypeEnum.shortvideo.toString())) {
            return responseMessageVideoService;
        }
        //未设定的消息响应
        else {
            log.info("消息类型" + key + "未实现响应类");
            //默认全部文本消息回复
            return responseMessageTextService;
        }
    }
}
