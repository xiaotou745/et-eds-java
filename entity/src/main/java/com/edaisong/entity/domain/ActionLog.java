package com.edaisong.entity.domain;

import java.util.Date;



public class ActionLog {
	private int userID;
	private String userName;
	private String className;
	private String methodName;
	private String param;
	private String exception;
	private String stackTrace;
	private Long executeTime;
	private String sourceSys;
	private int clientFrom;
	private String requestTime;
	private String requestEndTime;
	private String appServer;
	private int requestType;
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
	public int getClientFrom() {
		return clientFrom;
	}
	public void setClientFrom(int clientFrom) {
		this.clientFrom = clientFrom;
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
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
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
}
