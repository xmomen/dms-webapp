package com.xmomen.module.wx.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.xmomen.framework.utils.StringUtilsExt;

import java.io.IOException;

public class JsonUtils {

    /**
     * 字符串解析成对应的对象
     *
     * @param inputJson 需要解析的json字符串
     * @param t         泛型对象的class
     * @param <T>       泛型对象
     * @return 泛型对象
     */
    public static <T> T parseJSON(String inputJson, Class<T> t) {
        JsonMapper jsonMapper = new JsonMapper();
        try {
            return jsonMapper.readValue(convertSpecialCharacter(inputJson), t);
        } catch (IOException e) {
            throw new RuntimeException("非法数据提交:" + e.getMessage());
        }
    }

    public static <T> T parseJSON(String inputJson, TypeReference valueTypeRef) {
        if (StringUtilsExt.isEmpty(inputJson)) {
            return null;
        }
        JsonMapper jsonMapper = new JsonMapper();
        try {
            return jsonMapper.readValue(convertSpecialCharacter(inputJson), valueTypeRef);
        } catch (IOException e) {
            throw new RuntimeException("非法数据提交:" + e.getMessage());
        }
    }

    /**
     * 特殊字符转义
     *
     * @param inputJson 需要转义的json字符串
     * @return 转义后的字符串
     */
    private static String convertSpecialCharacter(String inputJson) {
        inputJson = inputJson.replace("\n", "\\\\n");
        inputJson = inputJson.replace("\r", "\\\\r");
        inputJson = inputJson.replace("\t", "");
        return inputJson;
    }

    /**
     * 对象->Json字符串转化
     *
     * @param obj 对象实例
     * @return Json字符串
     * @throws JsonProcessingException
     */
    public static String toJSON(Object obj) throws JsonProcessingException {
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.writeValueAsString(obj);
    }
}
