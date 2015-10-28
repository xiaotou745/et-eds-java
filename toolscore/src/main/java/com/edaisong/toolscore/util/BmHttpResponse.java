package com.edaisong.toolscore.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 北迈http 返回结果
 * 
 * @author wangyc
 *
 */
public class BmHttpResponse<T> {
	/**
	 * http status类型
	 */
	private int httpStatusCode = 0;
	/**
	 * http content
	 */
	private String httpResponseText = "";
	/**
	 * http response headers
	 */
	private Map<String, String> httpResponseHeaders = new HashMap<String, String>();

	/**
	 * 是否成功
	 */
	private boolean httpIsError = false;
	/**
	 * 错误信息
	 */
	private String httpErrorMessage = "";

	/**
	 * 用于临时存放处理的强类型结果
	 */
	private T result;

	public T getResult() {
		
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	/**
	 * 是否成功
	 */
	private boolean isSuccess = true;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int statusCode) {
		this.httpStatusCode = statusCode;
	}

	public String getHttpResponseText() {
		return httpResponseText;
	}

	public void setHttpResponseText(String responseText) {
		this.httpResponseText = responseText;
	}

	public Map<String, String> getHttpResponseHeaders() {
		return httpResponseHeaders;
	}

	public void setHttpResponseHeaders(Map<String, String> responseHeaders) {
		this.httpResponseHeaders = responseHeaders;
	}

	public boolean isHttpError() {
		return httpIsError;
	}

	public void setHttpIsError(boolean iserror) {
		this.httpIsError = iserror;
	}

	public String getHttpErrorMessage() {
		return httpErrorMessage;
	}

	public void setHttpErrorMessage(String errorMessage) {
		this.httpErrorMessage = errorMessage;
	}
}
