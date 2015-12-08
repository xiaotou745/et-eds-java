package com.edaisong.toolsentity.req;

import java.util.Date;

import com.edaisong.toolsentity.common.PagedRequestBase;
import com.mongodb.BasicDBObject;

public class PagedMongoLogReq extends PagedRequestBase{
private String sourceSys;
private int requestType;
private String requestUrl;
private String monthInfo;
private int exceptionShowType;

private BasicDBObject queryObject;
private BasicDBObject sortObject;
public BasicDBObject getQueryObject() {
	return queryObject;
}

public void setQueryObject(BasicDBObject queryObject) {
	this.queryObject = queryObject;
}

public BasicDBObject getSortObject() {
	return sortObject;
}

public void setSortObject(BasicDBObject sortObject) {
	this.sortObject = sortObject;
}

public int getRequestType() {
	return requestType;
}

public void setRequestType(int requestType) {
	this.requestType = requestType;
}

public String getSourceSys() {
	return sourceSys;
}

public void setSourceSys(String sourceSys) {
	this.sourceSys = sourceSys;
}

public String getRequestUrl() {
	return requestUrl;
}

public void setRequestUrl(String requestUrl) {
	this.requestUrl = requestUrl;
}

public String getMonthInfo() {
	return monthInfo;
}

public void setMonthInfo(String monthInfo) {
	this.monthInfo = monthInfo;
}

public int getExceptionShowType() {
	return exceptionShowType;
}

public void setExceptionShowType(int exceptionShowType) {
	this.exceptionShowType = exceptionShowType;
}
}
