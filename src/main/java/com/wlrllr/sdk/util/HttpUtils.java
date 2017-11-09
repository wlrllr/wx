package com.wlrllr.sdk.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.wlrllr.sdk.api.JSONObj;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.alibaba.fastjson.JSONObject;

/**
 * 发送服务的工具类
 * 
 * @author wlrllr
 * 
 */
public class HttpUtils {
	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	private static PoolingHttpClientConnectionManager connMgr = null;// 连接池
	private static HttpClientBuilder httpClientBuilder = null;// HttpClient生成器
	private static int MAX_TIMEOUT = 5000;// 连接超时时间(ms)

	/**
	 * 初始化参数
	 */
	static {
		// 设置连接池
		connMgr = new PoolingHttpClientConnectionManager();
		// 设置连接池大小
		connMgr.setMaxTotal(100);
		connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

		RequestConfig.Builder configBuilder = RequestConfig.custom();
		// 设置连接超时
		configBuilder.setConnectTimeout(MAX_TIMEOUT);
		// 设置读取超时
		configBuilder.setSocketTimeout(MAX_TIMEOUT);
		// 设置连接池获取连接实例的超时
		configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
		// 在提交之前，测试连接是否可用
		// configBuilder.setStaleConnectionCheckEnabled(true);
		httpClientBuilder = HttpClients.custom();
		httpClientBuilder.setConnectionManager(connMgr);
		httpClientBuilder.setDefaultRequestConfig(configBuilder.build());
	}

	/**
	 * 
	 * 方法用途: 得到HTTP连接<br>
	 * 实现步骤: <br>
	 * 
	 * @return 返回可关闭HTTP连接
	 */
	private static CloseableHttpClient getConnection() {
		return httpClientBuilder.build();
	}

	/**
	 * 上传文件及其他参数
	 * 
	 * @param url
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public static JSONObject uploadFile(String url, Map<String, Object> paramMap) {
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		if (paramMap != null && !paramMap.isEmpty()) {
			for (Entry<String, Object> entry : paramMap.entrySet()) {
				if (entry.getValue() instanceof File) {
					builder.addBinaryBody(entry.getKey(), (File) entry.getValue());
				} else if (entry.getValue() instanceof String) {
					builder.addTextBody(entry.getKey(), (String) entry.getValue());
				} else if (entry.getValue() instanceof InputStream) {
					builder.addBinaryBody(entry.getKey(), (InputStream) entry.getValue());
				}
			}
		}
		URI uri = URI.create(url);
		HttpPost httpPost = new HttpPost(uri);
		httpPost.setEntity(builder.build());
		httpPost.addHeader("Content-Type", "multipart/form-data;charset=utf-8");
		return request(httpPost);
	}

	private static JSONObject request(HttpRequestBase request) {
		String result = null;
		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = getConnection().execute(request);
			result = EntityUtils.toString(httpResponse.getEntity());
			StatusLine statusLine = httpResponse.getStatusLine();
			int code = statusLine.getStatusCode();
			if (code != HttpStatus.OK.value()) {
				logger.error("返回结果异常，http返回状态码为{}", code);
			}
		} catch (Exception e) {
			logger.error("发送请求异常", e);
		} finally {
			if (null != request) {
				request.releaseConnection();
			}
			if (null != httpResponse) {
				try {
					httpResponse.close();
				} catch (IOException e) {
					logger.error("关闭http response连接异常[" + e.getMessage() + "]", e);
				}
			}
		}
		return JSONObject.parseObject(result);
	}

	public static JSONObject post(String url, Map<String,Object> paramMap) {
		return post(url,paramMap.entrySet());
	}

	public static JSONObject post(String url, Set<Entry<String,Object>> param) {
		try {
			HttpPost httpPost = new HttpPost(URI.create(url.toString()));
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (param != null) {
				for (Entry<String, Object> entry : param) {
					nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			return request(httpPost);
		} catch (Exception e) {
			logger.error("发送请求异常", e);
		}
		return null;
	}

	public static JSONObject post(String url, JSONObj param) {
		try {
			HttpPost httpPost = new HttpPost(URI.create(url.toString()));
			httpPost.setEntity( new StringEntity(param.toJSONString(),"utf-8"));
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			return request(httpPost);
		} catch (Exception e) {
			logger.error("发送请求异常", e);
		}
		return null;
	}

	public static JSONObject get(String url) {
		try {
			URI uri = URI.create(url);
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			return request(httpGet);
		} catch (Exception e) {
			logger.error("发送请求异常", e);
		}
		return null;
	}

	/**
	 * 简易http连接池
	 */
	private static ThreadLocal<HttpClient> simpleClientLocal = new ThreadLocal<HttpClient>() {
		@Override
		protected HttpClient initialValue() {
			return HttpClients.createDefault();
		}
	};

	/**
	 * 获取一个简易http链接，可供外部灵活定制
	 * 
	 * @return
	 */
	public static HttpClient getSimpleHttpClient() {
		return simpleClientLocal.get();
	}

}
