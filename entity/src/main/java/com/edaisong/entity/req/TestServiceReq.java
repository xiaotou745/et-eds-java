package com.edaisong.entity.req;

import javax.xml.bind.annotation.XmlRootElement;

import com.edaisong.entity.common.RequestBase;
@XmlRootElement(name = "TestServiceReq")  
public class TestServiceReq extends RequestBase{
private	int RecordType;
private String OperateTime;
public int getRecordType() {
	return RecordType;
}
public void setRecordType(int recordType) {
	RecordType = recordType;
}
public String getOperateTime() {
	return OperateTime;
}
public void setOperateTime(String operateTime) {
	OperateTime = operateTime;
}
}
