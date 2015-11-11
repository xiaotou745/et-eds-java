package com.edaisong.entity.req;

public class ModifyTagReq {
public String tags;
public Integer userId;
public String optName;
public String getOptName() {
	return optName;
}
public void setOptName(String optName) {
	this.optName = optName;
}
public String getTags() {
	return tags;
}
public void setTags(String tags) {
	this.tags = tags;
}
public Integer getUserId() {
	return userId;
}
public void setUserId(Integer userId) {
	this.userId = userId;
}

}
