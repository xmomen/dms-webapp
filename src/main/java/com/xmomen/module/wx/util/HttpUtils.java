package com.xmomen.module.wx.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtils {
    // 请求超时时间
    private static final int REQUEST_TIME_OUT = 1 * 60 * 1000;

    // 连接超时时间
    private static final int CONN_TIME_OUT = 5 * 60 * 1000;

    // 数据传输时间
    private static final int SO_TIME_OUT = 10 * 60 * 1000;

    static Logger log = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 取得远程地址IP
     *
     * @param request HttpServletRequest
     * @return 远程地址IP
     */
    public static String getRemoteIp(HttpServletRequest request) {
        String remoteIp = request.getHeader("x-forwarded-for");
        if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = request.getHeader("X-Real-IP");
        }
        if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = request.getHeader("X-Forwarded-For");
        }
        if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = request.getHeader("Proxy-Client-IP");
        }
        if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = request.getHeader("HTTP_CLIENT_IP");
        }
        if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = request.getRemoteAddr();
        }
        if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = request.getRemoteHost();
        }
        return remoteIp;
    }

    /**
     * Post 请求
     *
     * @param url    URL地址
     * @param encode 编码格式
     * @param params 参数键值列表
     * @return 响应结果
     */
    public static String httpPost(String url, String encode, Map<String, Object> params) {
        HttpURLConnection con = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;

        //读取返回内容
        StringBuffer buffer = new StringBuffer();

        //尝试发送请求
        try {
            URL u = new URL(url);

            //建立连接
            con = (HttpURLConnection) u.openConnection();

            //设置请求方式 POST
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            osw = new OutputStreamWriter(con.getOutputStream(), encode);
            con.setConnectTimeout(3000);
            con.setReadTimeout(3000);
            //构建请求参数
            osw.write(getParams(params).toString());
            osw.flush();

            //按照encode获取返回内容
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), encode));

            String temp;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(br);
            IOUtils.closeQuietly(osw);
            closeConnect(con);
        }
        return buffer.toString();
    }

    /**
     * @param url    URL地址
     * @param encode 编码格式
     * @param params 参数键值列表
     * @return 响应结果
     */
    public static String httpGet(String url, String encode, Map<String, Object> params) {
        HttpURLConnection con = null;
        BufferedReader br = null;
        // 读取返回内容
        StringBuffer buffer = new StringBuffer();

        // 尝试发送请求
        try {
            //构建带请求参数的URL
            URL u = new URL(url + "?" + getParams(params).toString());

            //建立连接
            con = (HttpURLConnection) u.openConnection();

            //设置请求方式 GET
            con.setRequestMethod("GET");

            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setConnectTimeout(3000);
            con.setReadTimeout(3000);

            //按照encode获取返回内容
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), encode));

            String temp;
            while ((temp = br.readLine()) != null) {
                buffer.append(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(br);
            closeConnect(con);
        }
        return buffer.toString();
    }

    /**
     * GET请求
     *
     * @param url 请求url
     * @return 返回请求结果
     */
    public static String doGet(String url) {
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(url);
        String result = "";
        try {
            method.setRequestHeader("Content-Type", "text/html;charset=utf-8");

            //设置页面编码
            method.getParams().setContentCharset("UTF-8");

            HttpConnectionManagerParams managerParams = client.getHttpConnectionManager().getParams();

            // 设置连接超时时间(单位毫秒)
            managerParams.setConnectionTimeout(CONN_TIME_OUT);

            // 设置读数据超时时间(单位毫秒)
            managerParams.setSoTimeout(SO_TIME_OUT);

            client.executeMethod(method);

            //获取返回的JSON数据
            result = method.getResponseBodyAsString();
        } catch (Exception e) {
            log.error("GET请求发生系统异常：", e);
        } finally {
            try {
                // 释放连接
                method.releaseConnection();
            } catch (Exception ex) {
            }
        }
        return result;
    }

    /**
     * 获取参数
     *
     * @param params 键值对参数集合
     * @return 参数键值字符串
     */
    public static StringBuffer getParams(Map<String, Object> params) {
        StringBuffer sb = new StringBuffer();

        //拼装参数
        if (params != null) {
            for (Map.Entry<String, Object> e : params.entrySet()) {
                sb.append(e.getKey());
                sb.append("=");
                sb.append(e.getValue());
                sb.append("&");
            }
            sb.substring(0, sb.length() - 1);
        }

        return sb;
    }

    /**
     * POST请求
     *
     * @param url           请求url
     * @param paramsMap     请求参数MAP
     * @param jsonXMLString body json字符串
     * @return
     */
    public static String doPost(String url, Map<String, String> paramsMap, String jsonXMLString) {
        HttpPost httpPost = new HttpPost(url);
        // set header
        setHeader(httpPost, url);

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SO_TIME_OUT).setConnectTimeout(CONN_TIME_OUT).setConnectionRequestTimeout(REQUEST_TIME_OUT).setExpectContinueEnabled(false).build();

        // RequestConfig.DEFAULT
        httpPost.setConfig(requestConfig);

        // 响应内容
        String responseContent = null;
        String strRep = null;
        ThreadLocal<CloseableHttpClient> httpClient = new ThreadLocal<CloseableHttpClient>();
        try {
            if (paramsMap != null && jsonXMLString == null) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(getParamsList(paramsMap), "UTF-8");
                httpPost.setEntity(entity);
            }
            else {
                httpPost.setEntity(new StringEntity(jsonXMLString, "UTF-8"));
            }

            // 执行post请求
            CloseableHttpClient client = HttpConnectionManager.getHttpClient();
            httpClient.set(client);
            HttpResponse httpResponse = httpClient.get().execute(httpPost);

            // 获取响应消息实体
            HttpEntity entityRep = httpResponse.getEntity();
            if (entityRep != null) {
                responseContent = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

                // 获取HTTP响应的状态码
                int statusCode = httpResponse.getStatusLine().getStatusCode();

                if (statusCode == HttpStatus.SC_OK) {
                    strRep = responseContent;
                }
                else if ((statusCode == HttpStatus.SC_MOVED_TEMPORARILY)
                        || (statusCode == HttpStatus.SC_MOVED_PERMANENTLY)
                        || (statusCode == HttpStatus.SC_SEE_OTHER)
                        || (statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
                }
                // Consume response content
                EntityUtils.consume(entityRep);
                // Do not need the rest
                httpPost.abort();
            }
        } catch (Exception e) {
            log.error("POST请求发生系统异常：", e);
        } finally {
            httpPost.releaseConnection();
        }
        return strRep;
    }

    /**
     * 将传入的键/值对参数转换为NameValuePair参数集
     *
     * @param paramsMap 参数集, 键/值对
     * @return NameValuePair参数集
     */
    private static List<NameValuePair> getParamsList(
            Map<String, String> paramsMap) {
        if (paramsMap == null || paramsMap.size() == 0) {
            return null;
        }
        // 创建参数队列
        List<org.apache.http.NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> map : paramsMap.entrySet()) {
            params.add(new BasicNameValuePair(map.getKey(), map.getValue()));
        }
        return params;
    }

    /**
     * 请求头设置
     **/
    private static void setHeader(HttpRequestBase hg, String url) {
        if (null != hg) {
            hg.addHeader("Referer", url);
            hg.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            hg.addHeader("Connection", "keep-alive");
            hg.addHeader("Cache-Control", "max-age=0");
            hg.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
            hg.addHeader("Accept-Charset", "zh-GBK,utf-8;q=0.7,*;q=0.3");
        }
    }

    /**
     * 关闭HTTP连接
     *
     * @param con http连接
     */
    private static void closeConnect(HttpURLConnection con) {
        try {
            if (con != null) {
                con.disconnect();
            }
        } catch (Exception ex) {
            log.error("HttpURLConnection连接关闭失败：", ex);
        }
    }
}
