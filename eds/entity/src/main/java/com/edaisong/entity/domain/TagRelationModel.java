package com.edaisong.entity.domain;

public class TagRelationModel {
private int userId;
private int tagId;
private int isEnable;
private String optName;
private int userType;
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getTagId() {
	return tagId;
}
public void setTagId(int tagId) {
	this.tagId = tagId;
}
public int getIsEnable() {
	return isEnable;
}
public void setIsEnable(int isEnable) {
	this.isEnable = isEnable;
}
public String getOptName() {
	return optName;
}
public void setOptName(String optName) {
	this.optName = optName;
}
public int getUserType() {
	return userType;
}
public void setUserType(int userType) {
	this.userType = userType;
}
}
