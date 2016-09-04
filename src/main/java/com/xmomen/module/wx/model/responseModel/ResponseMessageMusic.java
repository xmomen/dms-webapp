package com.xmomen.module.wx.model.responseModel;


/**
 * 回复音乐消息
 * 格式：
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>12345678</CreateTime>
 * <MsgType><![CDATA[music]]></MsgType>
 * <Music>
 * <Title><![CDATA[TITLE]]></Title>
 * <Description><![CDATA[DESCRIPTION]]></Description>
 * <MusicUrl><![CDATA[MUSIC_Url]]></MusicUrl>
 * <HQMusicUrl><![CDATA[HQ_MUSIC_Url]]></HQMusicUrl>
 * <ThumbMediaId><![CDATA[media_id]]></ThumbMediaId>
 * </Music>
 * </xml>
 */
public class ResponseMessageMusic extends ResponseMessageBase {

    private Music music;

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    class Music {
        /**
         * 音乐名称
         */
        private String title;
        /**
         * 音乐描述
         */
        private String description;
        /**
         * 音乐链接
         */
        private String musicUrl;
        /**
         * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
         */
        private String hQMusicUrl;

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

        public String getMusicUrl() {
            return musicUrl;
        }

        public void setMusicUrl(String musicUrl) {
            this.musicUrl = musicUrl;
        }

        public String gethQMusicUrl() {
            return hQMusicUrl;
        }

        public void sethQMusicUrl(String hQMusicUrl) {
            this.hQMusicUrl = hQMusicUrl;
        }
    }

}
