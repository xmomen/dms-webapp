package com.xmomen.module.wx.util;

import com.xmomen.module.wx.model.responseModel.ResponseMessageBase;
import com.xmomen.module.wx.model.responseModel.ResponseMessageNews;
import com.xmomen.module.wx.model.responseModel.ResponseMessageText;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;

/**
 * 微信消息解析
 */
public class MessageUtils {
    /**
     * 回复消息封装微信格式
     */
    private static XStream xstream = new XStream(new XppDriver() {
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                @Override
                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    //节点属性 首字母大写 排除xml item
                    if (!"xml".equals(name) && !"item".equals(name)) {
                        name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    }
                    super.startNode(name, clazz);
                }

                @Override
                protected void writeText(QuickWriter writer, String text) {
                    // 对所有xml节点的转换都增加CDATA标记
                    writer.write("<![CDATA[");
                    writer.write(text);
                    writer.write("]]>");
                }
            };
        }
    });

    /**
     * 图文消息转换成xml
     *
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXml(ResponseMessageNews newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", newsMessage.new Article().getClass());
        return xstream.toXML(newsMessage);
    }

    /**
     * 文本消息转换成xml
     *
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(ResponseMessageText textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 将ResponseMessageBase实体转换成xml
     *
     * @param entity 封装好的消息
     * @return 解析组装之后的xml
     */
    public static String convertEntityToXml(ResponseMessageBase entity) {
        //回复文本消息
        if (entity instanceof ResponseMessageText) {
            return textMessageToXml((ResponseMessageText) entity);
        }
        //回复图文消息
        if (entity instanceof ResponseMessageNews) {
            return newsMessageToXml((ResponseMessageNews) entity);
        }
        return null;
    }
}
