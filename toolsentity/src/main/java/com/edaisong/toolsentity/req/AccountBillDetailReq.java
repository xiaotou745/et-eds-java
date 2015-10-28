package com.edaisong.toolsentity.req;
/**
 * 账单详情请求参数
 * 茹化肖
 * 2015年9月11日15:17:53
 * */
public class AccountBillDetailReq {
private int businessId;//商户ID
private int recordId;//流水ID
public int getBusinessId() {
	return businessId;
}
public void setBusinessId(int businessId) {
	this.businessId = businessId;
}
public int getRecordId() {
	return recordId;
}
public void setRecordId(int recordId) {
	this.recordId = recordId;
}
}
