package com.edaisong.entity.req;


import com.edaisong.entity.common.RequestBase;

public class GroupReq extends RequestBase{
	
private Long Id;
private String GroupName;
private String AppKey;
public Long getId() {
	return Id;
}
public void setId(Long id) {
	Id = id;
}
public String getGroupName() {
	return GroupName;
}
public void setGroupName(String groupName) {
	GroupName = groupName;
}
public String getAppKey() {
	return AppKey;
}
public void setAppKey(String appKey) {
	AppKey = appKey;
}

}
