package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;
/*
 * 骑士管理-冻结单管理分页列表参数
 * 茹化肖
 * */
public class PagedClienterForzenReq extends PagedRequestBase{
private String clienterName;
private Integer forzenStatus;
private Integer dateType;
private String startDate;
private String endDate;
private String clienterPhone;
public String getClienterPhone() {
	return clienterPhone;
}
public void setClienterPhone(String clienterPhone) {
	this.clienterPhone = clienterPhone;
}
public String getClienterName() {
	return clienterName;
}
public void setClienterName(String clienterName) {
	this.clienterName = clienterName;
}
public Integer getForzenStatus() {
	return forzenStatus;
}
public void setForzenStatus(Integer forzenStatus) {
	this.forzenStatus = forzenStatus;
}
public Integer getDateType() {
	return dateType;
}
public void setDateType(Integer dateType) {
	this.dateType = dateType;
}
public String getStartDate() {
	return startDate;
}
public void setStartDate(String startDate) {
	this.startDate = startDate;
}
public String getEndDate() {
	return endDate;
}
public void setEndDate(String endDate) {
	this.endDate = endDate;
}
}
