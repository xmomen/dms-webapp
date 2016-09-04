package com.xmomen.module.wx.model.messageTemplate;

import java.io.Serializable;

/**
 * 模板消息bean
 */
public class PushMessageTemplateModel implements Serializable {

    /**
     * 接收者
     */
    private String touser;

    /**
     * 模板ID
     */
    private String template_id;

    /**
     * 跳转URL
     */
    private String url;

    /**
     * 标题颜色
     */
    private String topcolor;

    /**
     * 数据类 内部类
     */
    private Data data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }

    /**
     * 第一个参数
     *
     * @param color 值颜色
     * @param value 值
     * @return
     */
    public First setFirst(String color, String value) {
        First first = new First();
        first.setColor(color);
        first.setValue(value);
        return first;
    }

    /**
     * 第二个填充参数
     *
     * @param color 值颜色
     * @param value 值
     * @return
     */
    public Keyword1 setKeyword1(String color, String value) {
        Keyword1 key1 = new Keyword1();
        key1.setColor(color);
        key1.setValue(value);
        return key1;
    }

    /**
     * 第三个填充参数
     *
     * @param color 值颜色
     * @param value 值
     * @return
     */
    public Keyword2 setKeyword2(String color, String value) {
        Keyword2 key2 = new Keyword2();
        key2.setColor(color);
        key2.setValue(value);
        return key2;
    }

    /**
     * 第四个填充参数
     *
     * @param color 值颜色
     * @param value 值
     * @return
     */
    public Remark setRemark(String color, String value) {
        Remark remark = new Remark();
        remark.setColor(color);
        remark.setValue(value);
        return remark;
    }

    /**
     * 设置数据
     *
     * @param first
     * @param key1
     * @param key2
     * @param remark
     */
    public void setDataVal(First first, Keyword1 key1, Keyword2 key2, Remark remark) {
        Data d = new Data();
        d.setFirst(first);
        d.setKeyword1(key1);
        d.setKeyword2(key2);
        d.setRemark(remark);
        setData(d);
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    /**
     * 参数基类
     */
    class BaseParam {

        private String value;

        private String color;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    class First extends BaseParam {
    }

    class Keyword1 extends BaseParam {
    }

    class Keyword2 extends BaseParam {
    }

    class Remark extends BaseParam {
    }

    /**
     * 数据封装类
     */
    class Data {

        private First first;

        private Keyword1 keyword1;

        private Keyword2 keyword2;

        private Remark remark;

        public First getFirst() {
            return first;
        }

        public void setFirst(First first) {
            this.first = first;
        }

        public Keyword1 getKeyword1() {
            return keyword1;
        }

        public void setKeyword1(Keyword1 keyword1) {
            this.keyword1 = keyword1;
        }

        public Keyword2 getKeyword2() {
            return keyword2;
        }

        public void setKeyword2(Keyword2 keyword2) {
            this.keyword2 = keyword2;
        }

        public Remark getRemark() {
            return remark;
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

    }
}