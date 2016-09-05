package com.xmomen.module.wx.constants;

/**
 * 事件类型(大小写完全对应微信请求的字符串)
 */
public enum RequestEventEnum {
    //订阅
    subscribe,

    //取消订阅
    unsubscribe,

    //上报地理位置
    LOCATION,

    //二维码扫描
    SCAN,

    //模板消息事件推送
    TEMPLATESENDJOBFINISH,

    /**
     * 用户点击自定义菜单后，微信会把点击事件推送给开发者
     */
    //点击菜单跳转链接时的事件推送
    VIEW,
    //点击菜单拉取消息时的事件推送
    CLICK,
    /**
     * 仅支持微信iPhone5.4.1以上版本，和Android5.4以上版本的微信用户，旧版本微信用户点击后将没有回应，开发者也不能正常接收到事件推送
     */
    //扫码推事件的事件推送
    scancode_push,
    //扫码推事件且弹出“消息接收中”提示框的事件推送
    scancode_waitmsg,
    //弹出系统拍照发图的事件推送
    pic_sysphoto,
    //弹出拍照或者相册发图的事件推送
    pic_photo_or_album,
    //弹出微信相册发图器的事件推送
    pic_weixin,
    //弹出地理位置选择器的事件推送
    location_select;

}
