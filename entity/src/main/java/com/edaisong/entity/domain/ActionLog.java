package com.edaisong.entity.domain;

public class ActionLog {
	private String methodName;
	private String param;
	private String exception;
	private String stackTrace;
	private Long executeTime;
	private String sourceSys;
	private int clientFrom;
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
	private String appServer;
}
