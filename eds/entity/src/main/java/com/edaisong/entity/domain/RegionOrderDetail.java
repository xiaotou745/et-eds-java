package com.edaisong.entity.domain;

public class RegionOrderDetail {
private Long orderRegionOneId;
private String oneName;
private Long orderRegionTwoId;
private String twoName;
private Integer status;
private Long num;
private Integer levelType;
public Long getOrderRegionOneId() {
	return orderRegionOneId;
}
public void setOrderRegionOneId(Long orderRegionOneId) {
	this.orderRegionOneId = orderRegionOneId;
}
public String getOneName() {
	return oneName;
}
public void setOneName(String oneName) {
	this.oneName = oneName;
}
public Long getOrderRegionTwoId() {
	return orderRegionTwoId;
}
public void setOrderRegionTwoId(Long orderRegionTwoId) {
	this.orderRegionTwoId = orderRegionTwoId;
}
public String getTwoName() {
	return twoName;
}
public void setTwoName(String twoName) {
	this.twoName = twoName;
}
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}
public Long getNum() {
	return num;
}
public void setNum(Long num) {
	this.num = num;
}
public Integer getLevelType() {
	return levelType;
}
public void setLevelType(Integer levelType) {
	this.levelType = levelType;
}
}
