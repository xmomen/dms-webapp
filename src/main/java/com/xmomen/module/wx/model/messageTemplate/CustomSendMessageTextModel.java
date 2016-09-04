package com.xmomen.module.wx.model.messageTemplate;

/**
 * 客服发送消息模板BEAN (文本消息）
 * {
 * "touser":"OPENID",
 * "msgtype":"text",
 * "text":
 * {
 * "content":"Hello World"
 * }
 * }
 */
public class CustomSendMessageTextModel extends CustomSendMessageBaseModel {


    Text text;

    public Text getText() {
        return text;
    }

    public void setContent(String content) {
        Text text = new Text();
        text.setContent(content);
        this.text = text;
    }

    //内容类
    public class Text {
        String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
