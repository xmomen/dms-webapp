package com.xmomen.module.wx.model.responseModel;

/**
 * 回复语音消息
 * 格式：
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>12345678</CreateTime>
 * <MsgType><![CDATA[voice]]></MsgType>
 * <Voice>
 * <MediaId><![CDATA[media_id]]></MediaId>
 * </Voice>
 * </xml>
 */
public class ResponseMessageVoice extends ResponseMessageBase {

    private Voice voice;

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    class Voice {
        /**
         * 通过上传多媒体文件，得到的id
         */
        private String mediaId;

        public String getMediaId() {
            return mediaId;
        }

        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }
    }
}
