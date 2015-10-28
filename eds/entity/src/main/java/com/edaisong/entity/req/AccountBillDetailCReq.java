package com.edaisong.entity.req;
/**
 * 账单详情请求参数
 * 茹化肖
 * 2015年9月11日15:17:53
 * */
public class AccountBillDetailCReq {
private int clienterId;
private int recordId;//流水ID
public int getClienterId() {
	return clienterId;
}
public void setClienterId(int clienterId) {
	this.clienterId = clienterId;
}
public int getRecordId() {
	return recordId;
}
public void setRecordId(int recordId) {
	this.recordId = recordId;
}
}
