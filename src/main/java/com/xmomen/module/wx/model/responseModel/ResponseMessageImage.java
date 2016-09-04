package com.xmomen.module.wx.model.responseModel;


/**
 * 回复图片消息
 * 格式如下：
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>12345678</CreateTime>
 * <MsgType><![CDATA[image]]></MsgType>
 * <Image>
 * <MediaId><![CDATA[media_id]]></MediaId>
 * </Image>
 * </xml>
 */
public class ResponseMessageImage extends ResponseMessageBase {

    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    class Image {
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
