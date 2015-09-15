package com.edaisong.entity.domain;

import java.util.Date;

import com.edaisong.core.enums.*;
import com.edaisong.core.util.ParseHelper;
/**
 * 日账单返回参数骑士
 * 
 * */
public class AccountBillDayCModel {
private String relationNo;
private int recordId;
public int getRecordId() {
	return recordId;
}
public void setRecordId(int recordId) {
	this.recordId = recordId;
}
private Double amount;
private int recordType;
private Date operateTime;
public String getRelationNo() {
	return relationNo;
}
public void setRelationNo(String relationNo) {
	this.relationNo = relationNo;
}
public Double getAmount() {
	return this.amount;
}
public void setAmount(Double amount) {
	this.amount = amount;
}
public String getRecordType() {
	return ClienterBalanceRecordRecordType.getEnum(this.recordType).desc();//.recordType;
}
public void setRecordType(int recordType) {
	this.recordType = recordType;
}
public String getOperateTime() {
	return ParseHelper.ToDateString(operateTime);
}
public void setOperateTime(Date operateTime) {
	this.operateTime = operateTime;
}

}
