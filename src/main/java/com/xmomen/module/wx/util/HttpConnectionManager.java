package com.xmomen.module.wx.util;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.nio.charset.CodingErrorAction;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.http.Consts;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

/**
 * @Title: HttpRequestUtils.java
 * @Package com.udf.wechat.util.http
 * @Description: TODO()
 * @author jizong.li 85150225@qq.com
 * @date 2014年10月11日 下午12:10:38
 * @version V1.0
 */
public final class HttpConnectionManager {


	private static final int MAX_TOTAL_CONNECTIONS = 800; //每个主机的最大并行链接数
	private static final int MAX_ROUTE_CONNECTIONS = 400; //客户端总并行链接最大数
	
	
	private static PoolingHttpClientConnectionManager connManager = null;
	
	static{
		SSLContextBuilder scb = new SSLContextBuilder();
		scb.useTLS();
		try {
			SSLContext sslContext = scb.loadTrustMaterial(null,new TrustStrategy(){

				//信任所有
				public boolean isTrusted(X509Certificate[] arg0, String arg1)
						throws CertificateException {
					return true;
				}
				
			}).build();
			
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)).build();
			
			//create connectionManager
			connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			
			// Create socket configuration
            SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
            connManager.setDefaultSocketConfig(socketConfig);
            
            // Create message constraints
            MessageConstraints messageConstraints = MessageConstraints.custom().setMaxHeaderCount(200).setMaxLineLength(2000).build();
		
            // Create connection configuration
            ConnectionConfig connectionConfig = ConnectionConfig.custom()
                    .setMalformedInputAction(CodingErrorAction.IGNORE)
                    .setUnmappableInputAction(CodingErrorAction.IGNORE)
                    .setCharset(Consts.UTF_8)
                    .setMessageConstraints(messageConstraints).build();

            connManager.setDefaultConnectionConfig(connectionConfig);
            connManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
            connManager.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 异常自动处理,使用httpRequestRetryHandler接口实现请求的异常恢复
	 */
	private static HttpRequestRetryHandler requestRetry = new HttpRequestRetryHandler() {

		/**
		 * 自定义的恢复策略
		 */
		public boolean retryRequest(IOException exception, int exceptionCount,
				HttpContext context) {
			if (exceptionCount >= 3)
				return false;
			if (exception instanceof InterruptedIOException) {
				return false;
			}
			if (exception instanceof UnknownHostException) {
				return false;
			}
			if (exception instanceof ConnectTimeoutException) {
				return false;
			}
			if (exception instanceof SSLException) {
				return false;
			}
			HttpClientContext clientContext = HttpClientContext.adapt(context);
			HttpRequest request = clientContext.getRequest();
			boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
			if (idempotent) {
				return true;
			}
			return false;
		}

	};
	
	public static CloseableHttpClient getHttpClient(){
		return HttpClients.custom().setConnectionManager(connManager)
									.setRetryHandler(requestRetry).build();
	}
	
}
