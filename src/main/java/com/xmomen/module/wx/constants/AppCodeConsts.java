package com.xmomen.module.wx.constants;

/**
 * 微信模板常量类
 */
public class AppCodeConsts {

    /**
     * 微信模块
     */
    public static final String APP_WX = "WX";

    public static final String MSG_TYPE = "msgType";

    public static final String MSG_ID = "msgId";

    public static final String FROM_USER_NAME = "fromUserName";

    public static final String CREATE_TIME = "createTime";

    //模板消息发送成功
    public final static int TEMPLATE_MESSAGE_SEND_SUCCESS = 1;

    //模板消息发送失败
    public final static int TEMPLATE_MESSAGE_SEND_FAIL = 0;

    //模板消息接收成功
    public final static int TEMPLATE_MESSAGE_RECEIVE_SUCCESS = 1;

    //模板消息接收失败
    public final static int TEMPLATE_MESSAGE_RECEIVE_FAIL = 0;

    //自动回复
    public final static int AUTO_REPLIED = 1;

    //快速回复
    public final static int QUICK_REPLIED = 1;

    //图片存储路径
    public final static String UPLOAD_IMAGE_ROOT_PATH = "/upload/weixinImage/";

    //语音存储路径
    public final static String UPLOAD_VOICE_ROOT_PATH = "/upload/weixinVoice/";

    //视频存储路径
    public final static String UPLOAD_VIDEO_ROOT_PATH = "/upload/weixinVideo/";

    //MP3文件后缀
    public final static String MP3 = ".mp3";

    //视频文件后缀
    public final static String MP4 = ".mp4";

    //图片文件后缀
    public final static String JPEG = ".JPEG";

    //文件夹分离器
    public final static String separator = "/";
}
