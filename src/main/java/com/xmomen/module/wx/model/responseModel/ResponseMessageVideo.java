package com.xmomen.module.wx.model.responseModel;


/**
 * 回复视频消息
 * 格式：
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>12345678</CreateTime>
 * <MsgType><![CDATA[video]]></MsgType>
 * <Video>
 * <MediaId><![CDATA[media_id]]></MediaId>
 * <Title><![CDATA[title]]></Title>
 * <Description><![CDATA[description]]></Description>
 * </Video>
 * </xml>
 */
public class ResponseMessageVideo extends ResponseMessageBase {
    /**
     * 视频
     */
    private Video video;

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    class Video {
        /**
         * 通过上传多媒体文件，得到的id
         */
        private String mediaId;

        /**
         * 视频消息的标题
         */
        private String title;

        /**
         * 视频消息的描述
         */
        private String description;

        public String getMediaId() {
            return mediaId;
        }

        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
