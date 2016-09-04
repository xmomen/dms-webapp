package com.xmomen.module.wx.service;

import com.alibaba.fastjson.JSONObject;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.wx.constants.AppCodeConsts;
import com.xmomen.module.wx.constants.RequestMsgTypeEnum;
import com.xmomen.module.wx.model.MessageBase;
import com.xmomen.module.wx.model.requestModel.RequestMessageBeanFactory;
import com.xmomen.module.wx.util.XMLUtils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信推送过来的消息处理类
 */
@Service
public class MessageHandlerService {
    static Logger log = LoggerFactory.getLogger(MessageHandlerService.class);

    /**
     * 请求消息缓存map 用于排重
     */
    private static Map<String, JSONObject> requestMessageMap = new HashMap<String, JSONObject>();

    @Autowired
    ResponseMessageServiceFactory responseMessageStrategyFactory;

    /**
     * 处理请求消息，并且返回回复信息
     *
     * @return 回复的消息
     */
    public String execute(InputStream is) {
        //得到请求的msg xml格式
        String xmlMsg = this.readInputStreamMsg(is);

        log.info("接收的到的XML格式请求消息：" + xmlMsg);

        //消息为空的话直接返回空串
        if (StringUtilsExt.isEmpty(xmlMsg)) {
            return StringUtilsExt.EMPTY;
        }

        JSONObject xmlJson = null;
        try {
            //xml格式转json
            xmlJson = XMLUtils.xmlToJSONObject(xmlMsg);

            log.info("转化后的请求消息(JSON格式)：" + xmlJson.toJSONString());
        } catch (Exception e) {
            log.error("解析XML字符串失败：", e);
            //直接回复空串
            return StringUtilsExt.EMPTY;
        }

        //请求类型
        String msgType = (String) xmlJson.get(AppCodeConsts.MSG_TYPE);

        //重复校验用MessageKey
        String messageKey = "";
        //请求是事件消息场景 没有msgid 取fromUserName + createTime为key
        if (msgType.toLowerCase().equals(RequestMsgTypeEnum.event.toString().toLowerCase())) {
            messageKey = (String) xmlJson.get(AppCodeConsts.FROM_USER_NAME) + xmlJson.get(AppCodeConsts.CREATE_TIME).toString();
        }
        //消息类型 取msgId未key
        else {
            messageKey = xmlJson.get(AppCodeConsts.MSG_ID).toString();
        }

        //重复消息场景
        if (this.isRepeatMessage(xmlJson, messageKey)) {
            log.info("微信请求消息重复。");
            return StringUtilsExt.EMPTY;
        }

        //请求model
        MessageBase requestMessageBase = null;
        try {
            String msgKey = msgType;

            //请求是事件消息场景
            if (msgType.toLowerCase().equals(RequestMsgTypeEnum.event.toString().toLowerCase())) {
                //具体的事件类型
                msgKey = RequestMessageBeanFactory.EVENT_PREFIX + xmlJson.get(RequestMsgTypeEnum.event.toString());
            }

            log.info("微信请求消息类型：" + msgKey);

            //将请求的消息封装成实体对象
            requestMessageBase = (MessageBase) JSONObject.parseObject(xmlJson.toJSONString(), RequestMessageBeanFactory.createInstance(msgKey).getClass());
        } catch (Exception e) {
            log.error("微信消息对象解析失败：", e);

            //将处理后的msg从缓存中移除
            requestMessageMap.remove(messageKey);

            //直接回复空串
            return StringUtilsExt.EMPTY;
        }

        try {
            //通过消息类型获取对应的响应处理类
            ResponseMessageBaseService responseMessageStrategy = responseMessageStrategyFactory.createInstance(msgType);


            //返回的回复消息
            String responseXml = responseMessageStrategy.handleRequestMessage(requestMessageBase);

            return responseXml;
        } catch (Exception e) {
            log.error("请求回复处理异常：", e);
        } finally {
            //将处理后的msg从缓存中移除
            requestMessageMap.remove(messageKey);
        }
        return StringUtilsExt.EMPTY;
    }


    /**
     * 消息重复判断 普通消息用msgid排重 事件消息用fromUserName+createTime排重
     *
     * @return true:重复/false:未重复
     */
    private boolean isRepeatMessage(JSONObject map, String key) {
        //不存在场景
        if (!requestMessageMap.containsKey(key)) {
            //放入缓存
            requestMessageMap.put(key, map);
            return false;
        }
        return true;
    }

    /**
     * 从流中读取微信消息
     *
     * @param is 微信请求消息输入流
     * @return 微信消息字符串(XML格式)
     */
    private String readInputStreamMsg(InputStream is) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            //获取输入流
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String line = null;

            //循环读取
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            log.error("从流中读取微信消息失败：", e);
        } finally {
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(is);
        }
        return sb.toString();
    }
}
