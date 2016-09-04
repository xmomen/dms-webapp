package com.xmomen.module.wx.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xmomen.framework.utils.StringUtilsExt;

/**
 * 过滤emoji 或者 其他非文字类型的字符
 */
public class EmojiFilter {
    static Logger log = LoggerFactory.getLogger(EmojiFilter.class);


    /**
     * 检测是否有emoji字符
     *
     * @param source 源字符串
     * @return true:有/false:无
     */
    public static boolean containsEmoji(String source) {
        //源字符串为空场景
        if (StringUtilsExt.isBlank(source)) {
            return false;
        }

        int len = source.length();

        //判断是否包含emoji字符
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            //包含emoji
            if (isEmojiCharacter(codePoint)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断是否是emoji字符（表情字符）
     *
     * @param codePoint 对象字符
     * @return true:是/false:否
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return !((codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF)));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source 字符串
     * @return 过滤后的字符串
     */
    public static String filterEmoji(String source) {
        log.info("待过滤的源字符串：" + source);

        //不包含场景
        if (!containsEmoji(source)) {
            return source;
        }

        StringBuilder buf = new StringBuilder(source.length());

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            //非表情字符的场合
            if (!isEmojiCharacter(codePoint)) {
                buf.append(codePoint);
            }
        }

        log.info("过滤后的字符串：" + buf.toString());

        return buf.toString();
    }
}