package com.edaisong.taobaoopenapi.entity;

import javax.xml.bind.annotation.XmlRootElement;

import com.edaisong.entity.common.RequestBase;

public class TestServiceReq extends OpenRequestBase{
private	int recordType;
public int getRecordType() {
	return recordType;
}
public void setRecordType(int recordType) {
	this.recordType = recordType;
}
public String getOperateTime() {
	return operateTime;
}
public void setOperateTime(String operateTime) {
	this.operateTime = operateTime;
}
private String operateTime;

}
