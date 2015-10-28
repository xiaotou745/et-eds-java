package com.edaisong.toolscore.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 此库是在apache httpclient的基础上进行封装,用于发起HTTP POST/GET请求.<br>
 * 默认的超时时间为2000ms<br>
 * 
 * @author wangyuchuan@beimai.com
 * @version 0.01
 * @since apache httpclient-4.3.6
 */
public class HttpRequestUtil {
	private final static Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);
	private static final int DEFAULT_TIMEOUT = 5000; // milliseconds

	/**
	 * @param requestUrl 必选参数，必须为非空字符串
	 * @return 返回请求信息(包括是否发生错误，错误原因,返回码等)
	 * @throws Exception
	 * @see BmHttpResponse
	 */
	public static <T> BmHttpResponse<T> httpRequestGet(String requestUrl,
			List<Cookie> sendCookies) throws Exception {
		return httpRequestGet(requestUrl, null, null, DEFAULT_TIMEOUT, sendCookies);
	}

	/**
	 * @param requestUrl 必选参数，必须为非空字符串
	 * @param requestParams 如果无参数请求，请传递空的HashMap或null
	 * @return 返回请求信息(包括是否发生错误，错误原因,返回码等)
	 * @throws Exception
	 * @see BmHttpResponse
	 */
	public static <T> BmHttpResponse<T> httpRequestGet(String requestUrl, Map<String, String> requestParams,
			List<Cookie> sendCookies)
			throws Exception {
		return httpRequestGet(requestUrl, requestParams, null, DEFAULT_TIMEOUT, sendCookies);
	}

	/**
	 * @param requestUrl 必选参数，必须为非空字符串
	 * @param requestParams 如果无参数请求，请传递空的HashMap或null
	 * @param requestHeaders 如果无header参数请求，请传递空的HashMap或null
	 * @return 返回请求信息(包括是否发生错误，错误原因,返回码等)
	 * @throws Exception
	 * @see BmHttpResponse
	 */
	public static <T> BmHttpResponse<T> httpRequestGet(String requestUrl,
			Map<String, String> requestParams,
			Map<String, String> requestHeaders,
			List<Cookie> sendCookies) throws Exception {
		return httpRequestGet(requestUrl, requestParams, requestHeaders, DEFAULT_TIMEOUT, sendCookies);
	}

	/**
	 * Execute http post by using Apache httpclient
	 * 
	 * @param requestUrl 必选参数，必须为非空字符串
	 * @param requestParams 如果无参数请求，请传递空的HashMap或null
	 * @param requestHeaders 如果无header参数请求，请传递空的HashMap或null
	 * @param requestTimeOut 如果无timeout参数，请传递0或负整数
	 * @return 返回请求信息(包括是否发生错误，错误原因,返回码等)
	 * @see BmHttpResponse
	 */
	public static <T> BmHttpResponse<T> httpRequestGet(String requestUrl, Map<String, String> requestParams,
			Map<String, String> requestHeaders, int requestTimeOut, List<Cookie> sendCookies) throws Exception {

		BmHttpResponse<T> response = new BmHttpResponse<T>();

		StringBuffer url = new StringBuffer(requestUrl);
		// 请求的参数信息传递
		if (requestParams != null) {
			for (String key : requestParams.keySet()) {
				String value = requestParams.get(key);
				if (url.indexOf("?") < 0) {
					url.append("?").append(key).append("=").append(value);
				} else {
					url.append("&").append(key).append("=").append(value);
				}
			}
		}

		HttpGet httpGet = new HttpGet(url.toString());
		httpGet.setHeader("chartset", "utf-8");

		// 默认超时时间为5s。
		if (requestTimeOut <= 0) {
			requestTimeOut = DEFAULT_TIMEOUT;
		}
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(requestTimeOut)
				.setConnectTimeout(requestTimeOut)
				.setConnectionRequestTimeout(requestTimeOut)
				.setStaleConnectionCheckEnabled(true)
				.build();
		httpGet.setConfig(requestConfig);

		// header设置
		if (requestHeaders != null) {
			for (String key : requestHeaders.keySet()) {
				httpGet.setHeader(key, requestHeaders.get(key)); // headers
			}
		}

		// create a client
		HttpClient client;
		if (sendCookies != null && sendCookies.size() > 0) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, 10);
			Date date = calendar.getTime();
			
			CookieStore cookieStore = new BasicCookieStore();

			for (Cookie cookie : sendCookies) {
				BasicClientCookie sendCookie = new BasicClientCookie(cookie.getName(), cookie.getValue());
				sendCookie.setPath("/");
				sendCookie.setDomain(".beimai.com");
				sendCookie.setExpiryDate(date);
				logger.debug("httpRequestGet add cookie with name:{} value:{}",cookie.getName(),cookie.getValue());
				cookieStore.addCookie(sendCookie);
			}

			client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		} else {
			client = HttpClients.createDefault();
		}
		/*
		 * HttpContext localContext = new BasicHttpContext();
		 */
		// 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
		/*
		 * client.getParams().setParameter( ClientPNames.COOKIE_POLICY,
		 * org.apache.http.client.params.CookiePolicy.BROWSER_COMPATIBILITY);
		 */
		try {
			HttpResponse httpResponse = client.execute(httpGet);
			// response code
			response.setHttpStatusCode(httpResponse.getStatusLine().getStatusCode());
			if (response.getHttpStatusCode() < 200 || response.getHttpStatusCode() >= 300) {
				throw new ClientProtocolException("Unexpected response status: " + response.getHttpStatusCode());
			}
			// body
			HttpEntity entity = httpResponse.getEntity();
			response.setHttpResponseText(entity != null ? EntityUtils.toString(entity) : "");
			// headers
			Header headers[] = httpResponse.getAllHeaders();
			for (Header h : headers) {
				response.setHttpResponseHeaders(new HashMap<String, String>());
				response.getHttpResponseHeaders().put(h.getName(), h.getValue());
			}
		} catch (Exception e) {
			response.setHttpIsError(true);
			response.setHttpErrorMessage("Failed to execute request:" + e.getMessage());
		}
		return response;
	}

	/**
	 * Http Post request
	 */
	/**
	 * @param requestUrl 必选参数，必须为非空字符串
	 * @return 返回请求信息(包括是否发生错误，错误原因,返回码等)
	 * @throws Exception
	 * @see BmHttpResponse
	 */
	public static <T> BmHttpResponse<T> httpRequestPost(String requestUrl, List<Cookie> sendCookies) throws Exception {
		return httpRequestPost(requestUrl, null, null, DEFAULT_TIMEOUT, sendCookies);
	}

	/**
	 * @param requestUrl requried
	 * @param requestParams 如果无参数请求，请传递空的HashMap或null
	 * @return 返回请求信息(包括是否发生错误，错误原因,返回码等)
	 * @throws Exception
	 * @see BmHttpResponse
	 */
	public static <T> BmHttpResponse<T> httpRequestPost(String requestUrl, Map<String, String> requestParams,
			List<Cookie> sendCookies)
			throws Exception {
		return httpRequestPost(requestUrl, requestParams, null, DEFAULT_TIMEOUT, sendCookies);
	}

	/**
	 * @param requestUrl 必选参数，必须为非空字符串
	 * @param requestParams 如果无参数请求，请传递空的HashMap或null
	 * @param requestHeaders 如果无header参数请求，请传递空的HashMap或null
	 * @return 返回请求信息(包括是否发生错误，错误原因,返回码等)
	 * @throws Exception
	 * @see BmHttpResponse
	 */
	public static <T> BmHttpResponse<T> httpRequestPost(String requestUrl,
			Map<String, String> requestParams,
			Map<String, String> requestHeaders, List<Cookie> sendCookies) throws Exception {
		return httpRequestPost(requestUrl, requestParams, requestHeaders, DEFAULT_TIMEOUT, sendCookies);
	}

	/**
	 * Execute http post by using Apache httpclient
	 * 
	 * @param requestUrl 必选参数，必须为非空字符串
	 * @param requestParams 如果无参数请求，请传递空的HashMap或null
	 * @param requestHeaders 如果无header参数请求，请传递空的HashMap或null
	 * @param requestTimeOut 如果无timeout参数，请传递0或负整数
	 * @return 返回请求信息(包括是否发生错误，错误原因,返回码等)
	 * @see BmHttpResponse
	 */
	public static <T> BmHttpResponse<T> httpRequestPost(String requestUrl, Map<String, String> requestParams,
			Map<String, String> requestHeaders, int requestTimeOut, List<Cookie> sendCookies) throws Exception {

		BmHttpResponse<T> response = new BmHttpResponse<T>();

		HttpPost httpPost = new HttpPost(requestUrl);
		httpPost.setHeader("chartset", "utf-8");

		// 默认超时时间为5s。
		if (requestTimeOut <= 0) {
			requestTimeOut = DEFAULT_TIMEOUT;
		}
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(requestTimeOut)
				.setConnectTimeout(requestTimeOut)
				.setConnectionRequestTimeout(requestTimeOut)
				.setStaleConnectionCheckEnabled(true)
				.build();
		httpPost.setConfig(requestConfig);

		// header设置
		if (requestHeaders != null) {
			for (String key : requestHeaders.keySet()) {
				httpPost.setHeader(key, requestHeaders.get(key)); // headers
			}
		}
		// 请求的参数信息传递
		List<NameValuePair> paires = new ArrayList<NameValuePair>();
		if (requestParams != null) {
			for (String key : requestParams.keySet()) {
				paires.add(new BasicNameValuePair(key, requestParams.get(key)));
			}
		}

		if (paires.size() > 0) {
			HttpEntity entity = new UrlEncodedFormEntity(paires, "utf-8");
			httpPost.setEntity(entity);
		}

		// create a client
		HttpClient client;
		if (sendCookies != null && sendCookies.size() > 0) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, 10);
			Date date = calendar.getTime();
			
			CookieStore cookieStore = new BasicCookieStore();

			for (Cookie cookie : sendCookies) {
				BasicClientCookie sendCookie = new BasicClientCookie(cookie.getName(), cookie.getValue());
				sendCookie.setPath("/");
				sendCookie.setDomain(".beimai.com");
				sendCookie.setExpiryDate(date);
				logger.debug("httpRequestPost add cookie with name:{} value:{}",cookie.getName(),cookie.getValue());
				cookieStore.addCookie(sendCookie);
			}

			client = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		} else {
			client = HttpClients.createDefault();
		}
		try {
			HttpResponse httpResponse = client.execute(httpPost);
			// response code
			response.setHttpStatusCode(httpResponse.getStatusLine().getStatusCode());
			if (response.getHttpStatusCode() < 200 || response.getHttpStatusCode() >= 300) {
				throw new ClientProtocolException("Unexpected response status: " + response.getHttpStatusCode());
			}
			// body
			HttpEntity entity = httpResponse.getEntity();
			response.setHttpResponseText(entity != null ? EntityUtils.toString(entity) : "");
			// headers
			Header headers[] = httpResponse.getAllHeaders();
			for (Header h : headers) {
				response.setHttpResponseHeaders(new HashMap<String, String>());
				response.getHttpResponseHeaders().put(h.getName(), h.getValue());
			}
		} catch (Exception e) {
			response.setHttpIsError(true);
			response.setHttpErrorMessage("Failed to execute request:" + e.getMessage());
		}
		return response;
	}
}
