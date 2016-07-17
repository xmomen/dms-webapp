package com.xmomen.module.base.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class HttpClient implements HttpRequest {

	private ThreadLocal<CloseableHttpClient> httpClient = new ThreadLocal<CloseableHttpClient>();

	private Logger logger = LoggerFactory.getLogger(HttpClient.class);

	private static final int REQUEST_TIME_OUT = 10 * 1000; // 请求超时时间
	private static final int CONN_TIME_OUT = 60 * 1000; // 连接超时时间
	private static final int SO_TIME_OUT = 60 * 1000; // 数据传输时间

	public HttpClient() {
		CloseableHttpClient client = HttpConnectionManager.getHttpClient();

		httpClient.set(client);
	}

	/**
	 * 使用ResponseHandler接口处理响应，HttpClient使用ResponseHandler会自动管理连接的释放，解决了对连接的释放管理
	 */
	private ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

		@SuppressWarnings("deprecation")
		public String handleResponse(HttpResponse response)
				throws ClientProtocolException, IOException {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String charset = EntityUtils.getContentCharSet(entity) == null ? "UTF-8"
						: EntityUtils.getContentCharSet(entity);
				return new String(EntityUtils.toByteArray(entity), charset);
			} else {
				return null;
			}
		}

	};

	public void close() throws IOException {
		httpClient.get().close();
	}

	/**
	 * @todo 请求头设置
	 * 
	 **/
	private void setHeader(HttpRequestBase hg, String url) {
		if (null != hg) {
			hg.addHeader("Referer", url);
			hg.addHeader("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			hg.addHeader("Connection", "keep-alive");
			hg.addHeader("Cache-Control", "max-age=0");
			hg.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
			hg.addHeader("Accept-Charset", "zh-GBK,utf-8;q=0.7,*;q=0.3");
//			hg.addHeader("content-type","application/json");
		}
	}

	/**
	 * 指定参数名GET方式请求数据
	 * 
	 * @param url
	 * @param paramsMap
	 *            QueryString
	 * @return
	 */
	public String doGet(String url, Map<String, String> paramsMap) {
		return doGet(invokeUrl(url, paramsMap));
	}
	
	/**
	 * 带参数的 get method
	 * @param url
	 * @param paramsMap
	 * @return
	 */
	public JSONObject doGetToJsonObject(String url,Map<String,String> paramsMap) {
		String result = doGet(invokeUrl(url,paramsMap));
		return JSON.parseObject(result);
	}
	
	/**
	 * get method
	 * @param url
	 * @return
	 */
	public JSONObject doGetToJsonObject(String url) {
		String result = doGet(url);
		return JSON.parseObject(result);
	}

	/**
	 * GET方式请求数据
	 * 
	 * @param url
	 */
	public String doGet(String url) {
		HttpGet httpGet = new HttpGet(url);
		// set header
		setHeader(httpGet, url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(SO_TIME_OUT).setConnectTimeout(CONN_TIME_OUT)
				.setConnectionRequestTimeout(REQUEST_TIME_OUT).build();
		httpGet.setConfig(requestConfig);

		// long responseLength = 0; // 响应长度
		String responseContent = ""; // 响应内容
		try {
			// 执行get请求
			responseContent = httpClient.get()
					.execute(httpGet, responseHandler);
		} catch (ClientProtocolException e) {
			logger.error("ClientProtocolException,GET请求URL:" + url
					+ "地址发生异常,异常信息:", e);
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException,GET请求URL:" + url
					+ "地址发生异常,异常信息:", e);
			e.printStackTrace();
		} catch (ConnectTimeoutException e) {
			logger.error("ConnectTimeoutException,GET请求URL:" + url
					+ "地址发生异常,异常信息:", e);
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			logger.error("SocketTimeoutException,GET请求URL:" + url
					+ "地址发生异常,异常信息:", e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Exception,GET请求URL:" + url + "地址发生异常,异常信息:", e);
		} finally {
			httpGet.abort();
			httpGet.releaseConnection();
		}

		return responseContent;
	}

	/**
	 * 不指定参数名的方式来POST数据
	 * 
	 * @param url
	 * @param jsonXMLString
	 * @return
	 */
	public String doPost(String url, String jsonXMLString) {
		return doPost(url, null, jsonXMLString);
	}
	
	/**
	 * post method return JSONObject
	 * @param url
	 * @param jsonXmlString
	 * @return
	 */
	public JSONObject doPostToJsonObject(String url,String jsonXmlString) {
		String result = doPost(url,null,jsonXmlString);
		return JSON.parseObject(result);
	}
	
	/**
	 * post method return JSONObject
	 * @param url
	 * @param paramsMap
	 * @return
	 */
	public JSONObject doPostToJsonObject(String url,Map<String,String> paramsMap) {
		String result = doPost(url,paramsMap,null);
		return JSON.parseObject(result);
	}

	/**
	 * 指定参数名POST方式请求数据
	 * 
	 * @param url
	 */
	public String doPost(String url, Map<String, String> paramsMap) {
		return doPost(url, paramsMap, null);
	}
	
	public String doPostParamsSetBody(String url,String data) {
		HttpPost httpPost = new HttpPost(url);
		setHeader(httpPost, url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(SO_TIME_OUT).setConnectTimeout(CONN_TIME_OUT)
				.setConnectionRequestTimeout(REQUEST_TIME_OUT)
				.setExpectContinueEnabled(false).build();
		httpPost.setConfig(requestConfig);// RequestConfig.DEFAULT
		String responseContent = null; // 响应内容
		String strRep = null;
		try {
			httpPost.setEntity(new StringEntity(data,"UTF-8"));

			// 执行post请求
			HttpResponse httpResponse = httpClient.get().execute(httpPost);

			// 获取响应消息实体
			HttpEntity entityRep = httpResponse.getEntity();
			if (entityRep != null) {
				//responseLength = entityRep.getContentLength();
				responseContent = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

				// 获取HTTP响应的状态码
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				if (statusCode == HttpStatus.SC_OK) {
					strRep = responseContent; // EntityUtils.toString(httpResponse.getEntity());

				} else if ((statusCode == HttpStatus.SC_MOVED_TEMPORARILY)
						|| (statusCode == HttpStatus.SC_MOVED_PERMANENTLY)
						|| (statusCode == HttpStatus.SC_SEE_OTHER)
						|| (statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
					// 重定向处理，获得跳转的网址
					// Header locationHeader =
					// httpResponse.getFirstHeader("Location");
					// if(locationHeader != null) {
					// String successUrl = locationHeader.getValue();
					// }
				}

				// Consume response content
				EntityUtils.consume(entityRep);
				// Do not need the rest
				httpPost.abort();
			}
		} catch (ClientProtocolException e) {
			logger.error("ClientProtocolException", e);
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException", e);
			e.printStackTrace();
		} catch (ConnectTimeoutException e) {
			logger.error("ConnectTimeoutException", e);
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			logger.error("SocketTimeoutException", e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Exception", e);
			e.printStackTrace();
		} finally {
			httpPost.releaseConnection();
		}

		return strRep;
	}

	/**
	 * 
	 * @param url
	 * @param paramsMap
	 * @param jsonXMLString
	 * @return
	 */
	private String doPost(String url, Map<String, String> paramsMap,String jsonXMLString) {
		HttpPost httpPost = new HttpPost(url);

		// set header
		setHeader(httpPost, url);

		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(SO_TIME_OUT).setConnectTimeout(CONN_TIME_OUT)
				.setConnectionRequestTimeout(REQUEST_TIME_OUT)
				.setExpectContinueEnabled(false).build();

		httpPost.setConfig(requestConfig);// RequestConfig.DEFAULT

		//long responseLength = 0; // 响应长度
		String responseContent = null; // 响应内容
		String strRep = null;
		try {
			if (paramsMap != null && jsonXMLString == null) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
						getParamsList(paramsMap), "UTF-8");
				httpPost.setEntity(entity);
			} else {
				httpPost.setEntity(new StringEntity(jsonXMLString,
						"UTF-8"));
			}

			// 执行post请求
			HttpResponse httpResponse = httpClient.get().execute(httpPost);

			// 获取响应消息实体
			HttpEntity entityRep = httpResponse.getEntity();
			if (entityRep != null) {
				//responseLength = entityRep.getContentLength();
				responseContent = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

				// 获取HTTP响应的状态码
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				if (statusCode == HttpStatus.SC_OK) {
					strRep = responseContent; // EntityUtils.toString(httpResponse.getEntity());

				} else if ((statusCode == HttpStatus.SC_MOVED_TEMPORARILY)
						|| (statusCode == HttpStatus.SC_MOVED_PERMANENTLY)
						|| (statusCode == HttpStatus.SC_SEE_OTHER)
						|| (statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
					// 重定向处理，获得跳转的网址
					// Header locationHeader =
					// httpResponse.getFirstHeader("Location");
					// if(locationHeader != null) {
					// String successUrl = locationHeader.getValue();
					// }
				}

				// Consume response content
				EntityUtils.consume(entityRep);
				// Do not need the rest
				httpPost.abort();
			}
		} catch (ClientProtocolException e) {
			logger.error("ClientProtocolException", e);
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			logger.error("UnsupportedEncodingException", e);
			e.printStackTrace();
		} catch (ConnectTimeoutException e) {
			logger.error("ConnectTimeoutException", e);
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			logger.error("SocketTimeoutException", e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Exception", e);
			e.printStackTrace();
		} finally {
			httpPost.releaseConnection();
		}

		return strRep;
	}

	/**
	 * 读取内容
	 * @param in
	 * @return
	 * @throws Exception
	 */
	protected static String readContent(InputStream in) throws Exception {
		BufferedInputStream buffer = new BufferedInputStream(in);
		StringBuilder builder = new StringBuilder();
		byte[] bytes = new byte[1024];
		int line = 0;
		while ((line = buffer.read(bytes)) != -1) {
			builder.append(new String(bytes, 0, line, "UTF-8"));
		}

		return builder.toString();
	}

	/**
	 * GET方式传参
	 * 
	 * @param url
	 * @param paramsMap
	 * @return
	 */
	public static String invokeUrl(String url, Map<String, String> paramsMap) {
		StringBuilder sb = new StringBuilder();
		sb.append(url);
		int i = 0;
		if (paramsMap != null && paramsMap.size() > 0) {
			for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
				if (i == 0 && !url.contains("?")) {
					sb.append("?");
				} else {
					sb.append("&");
				}
				sb.append(entry.getKey());
				sb.append("=");
				String value = entry.getValue();
				try {
					sb.append(URLEncoder.encode(value, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					try {
						sb.append(URLEncoder.encode(value, null));
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
				}

				i++;
			}
		}

		return sb.toString();
	}

	/**
	 * 将传入的键/值对参数转换为NameValuePair参数集
	 * 
	 * @param paramsMap
	 *            参数集, 键/值对
	 * @return NameValuePair参数集
	 */
	private static List<NameValuePair> getParamsList(
			Map<String, String> paramsMap) {
		if (paramsMap == null || paramsMap.size() == 0) {
			return null;
		}

		// 创建参数队列
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> map : paramsMap.entrySet()) {
			params.add(new BasicNameValuePair(map.getKey(), map.getValue()));
		}

		return params;
	}

	/**
	 * 格式化XML
	 * 
	 * @param inputXML
	 * @return
	 * @throws Exception
	 */
	public static String formatXML(String inputXML) throws Exception {
		Document doc = DocumentHelper.parseText(inputXML);
		StringWriter out = null;
		if (doc != null) {
			try {
				OutputFormat format = OutputFormat.createPrettyPrint();
				out = new StringWriter();
				XMLWriter writer = new XMLWriter(out, format);
				writer.write(doc);
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				out.close();
			}

			return out.toString();
		}

		return inputXML;
	}

	@Override
	public String doPost(String url) {
		// TODO Auto-generated method stub
		return null;
	}
}
