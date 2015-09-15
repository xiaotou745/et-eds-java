package com.edaisong.entity.domain;

import java.util.Date;
import com.edaisong.core.enums.ClienterBalanceRecordRecordType;
import com.edaisong.core.enums.ClienterBalanceRecordStatus;
import com.edaisong.core.util.ParseHelper;

/**
 * api 骑士 账单详情
 * @author 茹化肖
 * 2015年9月11日15:21:00
 *
 */
public class AccountBillDetailCModel {
private double amount;
private int status;
private int withwardId;
private String relationNo;
private int recordType;
private Date operateTime;
private String remark;
private String noDesc;
private int isOrder;
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public String getStatus() {
	return ClienterBalanceRecordStatus.getEnum(status).desc();
}
public void setStatus(int status) {
	this.status = status;
}
public int getWithwardId() {
	return withwardId;
}
public void setWithwardId(int withwardId) {
	this.withwardId = withwardId;
}
public String getRelationNo() {
	return relationNo;
}
public void setRelationNo(String relationNo) {
	this.relationNo = relationNo;
}
public String getRecordType() {
	return ClienterBalanceRecordRecordType.getEnum(this.recordType).desc();
}
public void setRecordType(int recordType) {
	this.recordType = recordType;
}
public String getOperateTime() {
	return ParseHelper.ToDateString(this.operateTime);
}
public void setOperateTime(Date operateTime) {
	this.operateTime = operateTime;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public String getNoDesc() {
	return noDesc;
}
public void setNoDesc(String noDesc) {
	this.noDesc = noDesc;
}
public int getIsOrder() {
	return isOrder;
}
public void setIsOrder(int isOrder) {
	this.isOrder = isOrder;
}
}
