package com.edaisong.entity.domain;

import com.edaisong.core.enums.*;;
/**
 * 日账单返回参数商户
 * 
 * */
public class AccountBillDayModel {
public String relationNo;
public Double Amount;
public int recordType;
public String operateTime;
public String getRelationNo() {
	return relationNo;
}
public void setRelationNo(String relationNo) {
	this.relationNo = relationNo;
}
public Double getAmount() {
	return Amount;
}
public void setAmount(Double amount) {
	Amount = amount;
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
