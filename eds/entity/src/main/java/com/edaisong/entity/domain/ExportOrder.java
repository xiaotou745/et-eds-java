package com.edaisong.entity.domain;

/**
 *  导出订单
 * @author CaoHeYang
 * @date
 */
public class ExportOrder {
private String orderNo;
private String businessInfo;
private String clienterInfo;
private String pubDate;
private String actualDoneDate;
private Double amount;
private Double totalAmount;
private Double orderCommission;
private Integer orderCount;
private Double distribSubsidy;
private Double websiteSubsidy;
private Integer adjustment;
public String getOrderNo() {
	return orderNo;
}
public void setOrderNo(String orderNo) {
	this.orderNo = orderNo;
}
public String getBusinessInfo() {
	return businessInfo;
}
public void setBusinessInfo(String businessInfo) {
	this.businessInfo = businessInfo;
}
public String getClienterInfo() {
	return clienterInfo;
}
public void setClienterInfo(String clienterInfo) {
	this.clienterInfo = clienterInfo;
}
public String getPubDate() {
	return pubDate;
}
public void setPubDate(String pubDate) {
	this.pubDate = pubDate;
}
public String getActualDoneDate() {
	return actualDoneDate;
}
public void setActualDoneDate(String actualDoneDate) {
	this.actualDoneDate = actualDoneDate;
}
public Double getAmount() {
	return amount;
}
public void setAmount(Double amount) {
	this.amount = amount;
}
public Double getTotalAmount() {
	return totalAmount;
}
public void setTotalAmount(Double totalAmount) {
	this.totalAmount = totalAmount;
}
public Double getOrderCommission() {
	return orderCommission;
}
public void setOrderCommission(Double orderCommission) {
	this.orderCommission = orderCommission;
}
public Integer getOrderCount() {
	return orderCount;
}
public void setOrderCount(Integer orderCount) {
	this.orderCount = orderCount;
}
public Double getDistribSubsidy() {
	return distribSubsidy;
}
public void setDistribSubsidy(Double distribSubsidy) {
	this.distribSubsidy = distribSubsidy;
}
public Double getWebsiteSubsidy() {
	return websiteSubsidy;
}
public void setWebsiteSubsidy(Double websiteSubsidy) {
	this.websiteSubsidy = websiteSubsidy;
}
public Integer getAdjustment() {
	return adjustment;
}
public void setAdjustment(Integer adjustment) {
	this.adjustment = adjustment;
}
public Double getSettleMoney() {
	return settleMoney;
}
public void setSettleMoney(Double settleMoney) {
	this.settleMoney = settleMoney;
}
private Double settleMoney;
}
