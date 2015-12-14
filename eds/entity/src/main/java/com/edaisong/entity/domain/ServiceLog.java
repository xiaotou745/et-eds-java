package com.edaisong.entity.domain;

public class ServiceLog {
	private String sourceSys;
	private String requestTime;
	private String requestEndTime;
	private String appServer;
	private String serviceBean;		
	private String exception;
	private String stackTrace;
	private Long executeTime;
	public String getServiceBean() {
		return serviceBean;
	}
	public void setServiceBean(String serviceBean) {
		this.serviceBean = serviceBean;
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
	public String getAppServer() {
		return appServer;
	}
	public void setAppServer(String appServer) {
		this.appServer = appServer;
	}
}
