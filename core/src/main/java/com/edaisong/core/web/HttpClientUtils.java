package com.edaisong.core.web;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URLDecoder;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

public class HttpClientUtils {
	public enum HttpMethod {
		GET,
		POST,
		HEAD,
		OPTIONS,
		PUT,
		DELETE,
		TRACE
	}

	
	private static String app_name = "";

	/**
	 * 获取app_name,默认PC
	 * 
	 * @return
	 */
	public static String getApp_name() {
		return app_name;
	}

	/**
	 * 设置app_name，默认PC
	 * 
	 * @param app_name
	 */
	public static void setApp_name(String app_name) {
		HttpClientUtils.app_name = app_name;
	}

	private static String app_version = "1.0";

	/**
	 * 获取app_version，默认1.0
	 * 
	 * @return
	 */
	public static String getApp_version() {
		return app_version;
	}

	/**
	 * 设置app_version，默认1.0
	 * 
	 * @param app_version
	 */
	public static void setApp_version(String app_version) {
		HttpClientUtils.app_version = app_version;
	}

	/**
	 * Post请求后台ServletAPI
	 * 
	 * @param request 原Servlet的HttpServletRequest
	 * @param response 原Servlet的HttpServletResponse
	 * @param urlComponent 请求ServletAPI的Key(相对地址)
	 * @return 返回ServletAPI的json返回值。
	 * @throws ClientProtocolException 请求servletAPI抛出http异常
	 * @throws IOException 请求servletAPI抛出io异常
	 */
	public static <T> BmHttpResponse<T> doPost(HttpServletRequest request, HttpServletResponse response,
			String urlComponent)
			throws Exception {
		return doPost(request, response, urlComponent, null, null);
	}

	/**
	 * Post请求后台ServletAPI
	 * 
	 * @param request 原Servlet的HttpServletRequest
	 * @param response 原Servlet的HttpServletResponse
	 * @param urlComponent 请求ServletAPI的Key(相对地址)
	 * @param callback 对处理结果的回调(HttpServletResponse)
	 * @param params Post给后台ServletAPI的key-value数据
	 * @return 如果callback为空，则返回ServletAPI的json返回值。
	 *         如果过callback不为空，则返回callback.HandleHttpResult(body)
	 * @throws Exception exception
	 */
	public static <T> BmHttpResponse<T> doPost(HttpServletRequest request, HttpServletResponse response,
			String urlComponent,
			HttpDataHandler<T> callback, Map<String, String> params)
			throws Exception {
		if (request != null) {
			request.setCharacterEncoding("UTF-8");
		}
		// 请求的headers
		Map<String, String> requestHeaders = getRequestHeaders(request);

		Map<String, String> cookies = CookieUtils.getSendCookieKvs(request);
		if (cookies != null && cookies.size() > 0) {
			requestHeaders.putAll(cookies);
		}

		// 请求的参数信息传递
		Map<String, String> requestParams = getRequestParams(request);
		if (params != null) {
			Set<String> keys = params.keySet();
			Iterator<String> iterator = keys.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				requestParams.put(key, params.get(key));
			}
		}
		// 请求的cookies
		List<Cookie> sendCookies = CookieUtils.getSendCookies(request);
		BmHttpResponse<T> httpResponse = HttpRequestUtil.httpRequestPost(urlComponent, requestParams, requestHeaders,
				sendCookies);

		if (callback != null) {
			return callback.HandleHttpResult(httpResponse);
		}

		return httpResponse;
	}

	/**
	 * Get请求后台ServletAPI
	 * 
	 * @param request 原Servlet的HttpServletRequest
	 * @param response 原Servlet的HttpServletResponse
	 * @param urlComponent 请求ServletAPI的Key(相对地址)
	 * @param callback 对处理结果的回调(HttpServletResponse)
	 * @return 如果callback为空，则返回ServletAPI的json返回值。
	 *         如果过callback不为空，则返回callback.HandleHttpResult(body)
	 * @throws Exception
	 */
	public static <T> BmHttpResponse<T> doGet(HttpServletRequest request, HttpServletResponse response,
			String urlComponent,
			HttpDataHandler<T> callback)
			throws Exception {
		if (request != null) {
			request.setCharacterEncoding("UTF-8");
		}

		// 请求的headers
		Map<String, String> requestHeaders = getRequestHeaders(request);
		Map<String, String> cookies = CookieUtils.getSendCookieKvs(request);
		if (cookies != null && cookies.size() > 0) {
			requestHeaders.putAll(cookies);
		}

		// 请求的参数信息传递
		//Map<String, String> requestParams = getRequestParams(request);
		Map<String, String> requestParams = new HashMap<String, String>();

		// 请求的cookies
		List<Cookie> sendCookies = CookieUtils.getSendCookies(request);

		BmHttpResponse<T> httpRequestGet = HttpRequestUtil.httpRequestGet(urlComponent, requestParams, requestHeaders,
				sendCookies);

		if (callback != null) {
			return callback.HandleHttpResult(httpRequestGet);
		}
		return httpRequestGet;
	}

	/**
	 * Get请求后台ServletAPI
	 * 
	 * @param request 原Servlet的HttpServletRequest
	 * @param res 原Servlet的HttpServletResponse
	 * @param urlComponent 请求ServletAPI的Key(相对地址)
	 * @return 返回ServletAPI的json返回值。
	 */
	public static <T> BmHttpResponse<T> doGet(HttpServletRequest request, HttpServletResponse res, String urlComponent)
			throws Exception {
		return doGet(request, res, urlComponent, null);
	}

	/**
	 * 构造请求链接
	 * 
	 * @param requestMethod
	 * @param requestUrl
	 * @param requestParams
	 * @param requestHeaders
	 * @return
	 */
	@SuppressWarnings("unused")
	private static HttpUriRequest createHttpUriRequest(String requestMethod, String requestUrl,
			HashMap<String, String> requestHeaders) {

		RequestBuilder requestBuilder = RequestBuilder.create(requestMethod); // method
		requestBuilder.setUri(requestUrl); // url
		if (requestHeaders != null) {
			for (String key : requestHeaders.keySet()) {
				requestBuilder.setHeader(key, requestHeaders.get(key)); // headers
			}
		}

		RequestConfig requestConfigBuilder = RequestConfig.custom()
				.setConnectionRequestTimeout(5000) // timeout
				.setSocketTimeout(5000)
				.setConnectTimeout(5000)
				.setRedirectsEnabled(true)
				.build(); // redirect

		requestBuilder.setConfig(requestConfigBuilder);

		// create a request
		HttpUriRequest request = requestBuilder.build();
		return request;
	}

	private static Map<String, String> getRequestHeaders(HttpServletRequest request) {
		Map<String, String> requestHeaders = new HashMap<String, String>();

		requestHeaders.put("charset", "utf-8");
		// 增加client_ip 和 client_url
		if (request != null) {
			requestHeaders.put("clientIP", request.getRemoteHost());
			requestHeaders.put("clientUri", request.getRequestURI().toString());
			requestHeaders.put("ClientUrl", request.getRequestURL().toString());
		}

		// 设置app_name和app_version
		requestHeaders.put("appName", getApp_name());
		requestHeaders.put("appVersion", getApp_version());

		return requestHeaders;
	}

	/**
	 * 根据HTTP请求获取相关参数列表
	 * 
	 * @param request HTTP请求
	 * @return 返回HashMap类型的列表
	 * @throws ClientProtocolException 请求servletAPI抛出http异常
	 * @throws IOException 请求servletAPI抛出io异常
	 */
	public static Map<String, String> getRequestParams(HttpServletRequest request)
			throws ServletException, IOException {

		HashMap<String, String> requestParams = new HashMap<String, String>();

		if (request != null) {
			Enumeration<String> paramNames = request.getParameterNames();

			String paramName;
			String paramValue;
			if (paramNames != null) {
				while (paramNames.hasMoreElements()) {
					paramName = paramNames.nextElement();
					paramValue = URLDecoder.decode(request.getParameter(paramName), "UTF-8");
					requestParams.put(paramName, paramValue);
				}
			}
		}

		return requestParams;
	}


	
}
