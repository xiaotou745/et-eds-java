package com.edaisong.toolsentity.req;

import java.util.Date;

import com.mongodb.BasicDBObject;

public class MongoLogReq {
	private String sourceSys;
	private int requestType;
	private String requestUrl;
	private Date begin;
	private Date end;
	private int exceptionShowType;
	private int minStep;
	private String appversion;
	private BasicDBObject queryObject;
	public String getSourceSys() {
		return sourceSys;
	}

	public void setSourceSys(String sourceSys) {
		this.sourceSys = sourceSys;
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

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public int getExceptionShowType() {
		return exceptionShowType;
	}

	public void setExceptionShowType(int exceptionShowType) {
		this.exceptionShowType = exceptionShowType;
	}

	public BasicDBObject getQueryObject() {
		return queryObject;
	}

	public void setQueryObject(BasicDBObject queryObject) {
		this.queryObject = queryObject;
	}

	public int getMinStep() {
		return minStep;
	}

	public void setMinStep(int minStep) {
		this.minStep = minStep;
	}

	public String getAppversion() {
		return appversion;
	}

	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}


}
