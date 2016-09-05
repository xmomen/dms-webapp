package com.xmomen.module.wx.model.requestModel;

import com.xmomen.module.wx.constants.RequestEventEnum;
import com.xmomen.module.wx.constants.RequestMsgTypeEnum;
import com.xmomen.module.wx.model.MessageBase;
import com.xmomen.module.wx.model.requestModel.event.*;
import com.xmomen.module.wx.model.requestModel.message.*;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 接收服务器请求baen转换工厂
 */
public class RequestMessageBeanFactory {

    public final static String EVENT_PREFIX = "event_";
    static ConcurrentHashMap<String, Class<?>> requestMap = new ConcurrentHashMap<String, Class<?>>();

    static {
        //文本消息
        requestMap.put(RequestMsgTypeEnum.text.toString(), RequestMessageText.class);
        //图片消息
        requestMap.put(RequestMsgTypeEnum.image.toString(), RequestMessageImage.class);
        //语音消息
        requestMap.put(RequestMsgTypeEnum.voice.toString(), RequestMessageVoice.class);
        //短视频消息
        requestMap.put(RequestMsgTypeEnum.shortvideo.toString(), RequestMessageVideo.class);
        //视频消息
        requestMap.put(RequestMsgTypeEnum.video.toString(), RequestMessageVideo.class);
        //位置消息
        requestMap.put(RequestMsgTypeEnum.location.toString(), RequestMessageLocation.class);
        //链接消息
        requestMap.put(RequestMsgTypeEnum.link.toString(), RequestMessageLink.class);

        //关注事件请求封装类
        requestMap.put(EVENT_PREFIX + RequestEventEnum.subscribe.toString(), RequestMessageEventBase.class);
        //取消关注事件请求封装类
        requestMap.put(EVENT_PREFIX + RequestEventEnum.unsubscribe.toString(), RequestMessageEventBase.class);
        //扫描带参数二维码事件封装类
        requestMap.put(EVENT_PREFIX + RequestEventEnum.SCAN.toString(), RequestMessageEventScan.class);
        //主动上报地理位置事件封装类
        requestMap.put(EVENT_PREFIX + RequestEventEnum.LOCATION.toString(), RequestMessageEventLocation.class);
        //发送模板消息，反馈事件封装类
        requestMap.put(EVENT_PREFIX + RequestEventEnum.TEMPLATESENDJOBFINISH.toString(), RequestMessageEventTemplateSendJobFinish.class);
        //自定义菜单 拉取消息事件封装类
        requestMap.put(EVENT_PREFIX + RequestEventEnum.CLICK.toString(), RequestMessageEventBase.class);
        //自定义菜单 跳转链接事件封装类
        requestMap.put(EVENT_PREFIX + RequestEventEnum.VIEW.toString(), RequestMessageEventBase.class);
        //自定义菜单 扫码推事件的事件推送封装类
        requestMap.put(EVENT_PREFIX + RequestEventEnum.scancode_push.toString(), RequestMessageEventScanCode.class);
        //自定义菜单 扫码推事件且弹出“消息接收中”提示框的事件推送封装类
        requestMap.put(EVENT_PREFIX + RequestEventEnum.scancode_waitmsg.toString(), RequestMessageEventScanCode.class);
    }

    public static MessageBase createInstance(String key) throws InstantiationException, IllegalAccessException {
        //未配置请求类型
        if (!requestMap.containsKey(key)) {
            return RequestMessageEventBase.class.newInstance();
        }
        //配置请求类型
        else {
            return (MessageBase) requestMap.get(key).newInstance();
        }
    }
}
