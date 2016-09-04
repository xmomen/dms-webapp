package com.xmomen.module.wx.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.json.XMLTokener;

/**
 * 将XML的toJSONObject 方法改写下 以便满足微信的xml解析
 */
public class XMLUtils extends XML {

    public static com.alibaba.fastjson.JSONObject xmlToJSONObject(String string) throws JSONException {

        JSONObject jo = new JSONObject();

        XMLTokener x = new XMLTokener(string);

        while (x.more() && x.skipPast("<")) {
            parse(x, jo, (String) null);
        }
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(jo.toString());
        return jsonObject;
    }

    /**
     * XML内置parse方法(定制化)
     *
     * @param x
     * @param context
     * @param name
     * @return
     * @throws JSONException
     */
    private static boolean parse(XMLTokener x, JSONObject context, String name) throws JSONException {
        JSONObject jsonobject = null;
        Object token = x.nextToken();
        String string;
        if (token == BANG) {
            char c = x.next();
            if (c == 45) {
                if (x.next() == 45) {
                    x.skipPast("-->");
                    return false;
                }

                x.back();
            }
            else if (c == 91) {
                token = x.nextToken();
                if ("CDATA".equals(token) && x.next() == 91) {
                    string = x.nextCDATA();
                    if (string.length() > 0) {
                        context.accumulate("content", string);
                    }

                    return false;
                }

                throw x.syntaxError("Expected \'CDATA[\'");
            }

            int i = 1;

            do {
                token = x.nextMeta();
                if (token == null) {
                    throw x.syntaxError("Missing \'>\' after \'<!\'.");
                }

                if (token == LT) {
                    ++i;
                }
                else if (token == GT) {
                    --i;
                }
            } while (i > 0);

            return false;
        }
        else if (token == QUEST) {
            x.skipPast("?>");
            return false;
        }
        else if (token == SLASH) {
            token = x.nextToken();
            String endTagName = (String) token;
            //xml剔除
            if ("xml".equals(endTagName)) {
                return true;
            }
            //将微信的请求消息的首字母改为小写
            endTagName = endTagName.substring(0, 1).toLowerCase() + endTagName.substring(1);
            if (name == null) {
                throw x.syntaxError("Mismatched close tag " + token);
            }
            else if (!endTagName.equals(name)) {
                throw x.syntaxError("Mismatched " + name + " and " + token);
            }
            else if (x.nextToken() != GT) {
                throw x.syntaxError("Misshaped close tag");
            }
            else {
                return true;
            }
        }
        else if (token instanceof Character) {
            throw x.syntaxError("Misshaped tag");
        }
        else {
            String tagName = (String) token;
            //剔除xml节点
            if ("xml".equals(tagName)) {
                return true;
            }
            //将微信的请求消息的首字母改为小写
            tagName = tagName.substring(0, 1).toLowerCase() + tagName.substring(1);

            token = null;
            jsonobject = new JSONObject();

            while (true) {
                if (token == null) {
                    token = x.nextToken();
                }

                if (!(token instanceof String)) {
                    if (token == SLASH) {
                        if (x.nextToken() != GT) {
                            throw x.syntaxError("Misshaped tag");
                        }

                        if (jsonobject.length() > 0) {
                            context.accumulate(tagName, jsonobject);
                        }
                        else {
                            context.accumulate(tagName, "");
                        }

                        return false;
                    }

                    if (token != GT) {
                        throw x.syntaxError("Misshaped tag");
                    }

                    while (true) {
                        token = x.nextContent();
                        if (token == null) {
                            if (tagName != null) {
                                throw x.syntaxError("Unclosed tag " + tagName);
                            }

                            return false;
                        }

                        if (token instanceof String) {
                            string = (String) token;
                            //剔除xml节点
                            if (!"xml".equals(string)) {
                                //将微信的请求消息的首字母改为小写
                                string = string.substring(0, 1).toLowerCase() + string.substring(1);
                                if (string.length() > 0) {
                                    jsonobject.accumulate("content", JSONObject.stringToValue(string));
                                }
                            }
                        }
                        else if (token == LT && parse(x, jsonobject, tagName)) {
                            if (jsonobject.length() == 0) {
                                context.accumulate(tagName, "");
                            }
                            else if (jsonobject.length() == 1 && jsonobject.opt("content") != null) {
                                context.accumulate(tagName, jsonobject.opt("content"));
                            }
                            else {
                                context.accumulate(tagName, jsonobject);
                            }

                            return false;
                        }
                    }
                }

                string = (String) token;
                token = x.nextToken();
                if (token == EQ) {
                    token = x.nextToken();
                    if (!(token instanceof String)) {
                        throw x.syntaxError("Missing value");
                    }

                    jsonobject.accumulate(string, JSONObject.stringToValue((String) token));
                    token = null;
                }
                else {
                    jsonobject.accumulate(string, "");
                }
            }
        }
    }
}
