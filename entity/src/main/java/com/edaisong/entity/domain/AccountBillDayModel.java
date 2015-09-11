package com.edaisong.entity.domain;

import com.edaisong.core.enums.*;;
/**
 * 日账单返回参数商户
 * 
 * */
public class AccountBillDayModel {
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
private String operateTime;
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
	return BusinessBalanceRecordRecordType.getEnum(this.recordType).desc();//.recordType;
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

}
