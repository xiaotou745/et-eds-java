package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

public class PagedBusTaskListReq extends PagedRequestBase{
private String startDate;//开始时间
private String endDate;//结束时间
private int selectType;// 1 门店名称2 门店电话
private String selectValue;//
private String cityName;//城市名称
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
public int getSelectType() {
	return selectType;
}
public void setSelectType(int selectType) {
	this.selectType = selectType;
}
public String getSelectValue() {
	return selectValue;
}
public void setSelectValue(String selectValue) {
	this.selectValue = selectValue;
}
public String getCityName() {
	return cityName;
}
public void setCityName(String cityName) {
	this.cityName = cityName;
}
}
