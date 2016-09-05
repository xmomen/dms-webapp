package com.xmomen.module.wx.util;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.wx.constants.WeixinConsts;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 微信上传下载多媒体文件工具类
 */
public class WeixinMediaUtils {

    static Logger log = LoggerFactory.getLogger(WeixinMediaUtils.class);

    /**
     * 获取媒体文件
     *
     * @param accessToken 访问令牌
     * @param mediaId     媒体ID
     * @param file        存储文件
     * @return 媒体文件类型
     */
    public static String downloadMedia(String accessToken, String mediaId, File file) {
        String requestUrl = WeixinConsts.GET_MEDIA_FILE.replace("{ACCESS_TOKEN}", accessToken).replace("{MEDIA_ID}", mediaId);

        HttpURLConnection conn = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        try {
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");

            //类型
            String contentType = conn.getHeaderField("Content-Type");

            bis = new BufferedInputStream(conn.getInputStream());
            fos = new FileOutputStream(file);
            byte[] buf = new byte[8096];
            int size = 0;
            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
            return contentType;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(fos);
            IOUtils.closeQuietly(bis);
            try {
                conn.disconnect();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return StringUtilsExt.EMPTY;
    }

    /**
     * 通过类型获取文件后缀
     *
     * @param contentType
     * @return
     */
    private static String getFileEndWitsh(String contentType) {
        String witsh = contentType.split("/")[1];
        return "." + witsh;
    }
}
