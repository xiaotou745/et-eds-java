package com.edaisong.entity.req;
/*
 * 骑士月账单请求参数
 * */
public class AccountBillCReq {
private String monthInfo;//月信息 2015-08
private Integer clienterId;//商户ID
public String getMonthInfo() {
	return monthInfo;
}
public void setMonthInfo(String monthInfo) {
	this.monthInfo = monthInfo;
}
public Integer getClienterId() {
	return clienterId;
}
public void setClienterId(Integer clienterId) {
	this.clienterId = clienterId;
}
}