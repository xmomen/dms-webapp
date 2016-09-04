package com.xmomen.module.wx.constants;

/**
 * 接收到的消息类型
 */
public enum RequestMsgTypeEnum {
    //事件推送
    event,

    //文本
    text,

    //图片
    image,

    //语音
    voice,

    //小视频
    shortvideo,

    //视频
    video,

    //链接
    link,

    //地理位置
    location;
}
