package com.edaisong.entity.domain;

import java.util.Date;



public class ActionLog {
	private int userID;
	private String userName;
	private String methodName;		
	private String param;
	private String decryptMsg;
	private String exception;
	private String stackTrace;
	private Long executeTime;
	private String sourceSys;
	private String requestUrl;
	private String requestTime;
	private String requestEndTime;
	private String appServer;
	private int requestType;
	private String clientIp;
	private String requestMethod;
	private String contentType;
	private String header;
	private String resultJson;
	public String getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getResultJson() {
		return resultJson;
	}
	public void setResultJson(String resultJson) {
		this.resultJson = resultJson;
	}

	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getStackTrace() {
		return stackTrace;
	}
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
	public Long getExecuteTime() {
		return executeTime;
	}
	public void setExecuteTime(Long executeTime) {
		this.executeTime = executeTime;
	}
	public String getSourceSys() {
		return sourceSys;
	}
	public void setSourceSys(String sourceSys) {
		this.sourceSys = sourceSys;
	}
	public String getAppServer() {
		return appServer;
	}
	public void setAppServer(String appServer) {
		this.appServer = appServer;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getRequestEndTime() {
		return requestEndTime;
	}
	public void setRequestEndTime(String requestEndTime) {
		this.requestEndTime = requestEndTime;
	}
	public int getRequestType() {
		return requestType;
	}
	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getDecryptMsg() {
		return decryptMsg;
	}
	public void setDecryptMsg(String decryptMsg) {
		this.decryptMsg = decryptMsg;
	}
}
