package com.xmomen.module.wx.constants;

/**
 * 事件类型
 */
public enum EventEnum {

    //订阅
    subscribe,

    //取消订阅
    unsubscribe,

    //自定义菜单点击事件
    CLICK,

    //上报地理位置
    location,

    //点击菜单跳转链接时的事件推送
    VIEW,

    //二维码扫描
    scan,

    //模板消息事件推送
    TEMPLATESENDJOBFINISH;
}
