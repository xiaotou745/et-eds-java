package com.edaisong.entity.domain;

import com.edaisong.entity.Business;

public class BusinessModel extends Business{
private String groupname;
private String businessgroupName;
public String getBusinessgroupName() {
	return businessgroupName;
}
public void setBusinessgroupName(String businessgroupName) {
	this.businessgroupName = businessgroupName;
}
public String getGroupname() {
	return groupname;
}
public void setGroupname(String groupname) {
	this.groupname = groupname;
}

}
