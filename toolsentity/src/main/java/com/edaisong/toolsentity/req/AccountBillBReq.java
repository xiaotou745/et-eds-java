package com.edaisong.toolsentity.req;
/*
 * 商户月账单请求参数
 * */
public class AccountBillBReq {
private String monthInfo;//月信息 2015-08
private Integer businessId;//商户ID
public String getMonthInfo() {
	return monthInfo;
}
public void setMonthInfo(String monthInfo) {
	this.monthInfo = monthInfo;
}
public Integer getBusinessId() {
	return businessId;
}
public void setBusinessId(Integer businessId) {
	this.businessId = businessId;
}
}
