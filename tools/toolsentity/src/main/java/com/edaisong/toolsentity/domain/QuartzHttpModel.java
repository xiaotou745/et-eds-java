package com.edaisong.toolsentity.domain;

import java.io.Serializable;
import java.util.List;

import com.edaisong.toolsentity.QuartzServiceModel;

public class QuartzHttpModel implements Serializable{
private String versionCode;
private List<QuartzServiceModel> listData;
public String getVersionCode() {
	return versionCode;
}
public void setVersionCode(String versionCode) {
	this.versionCode = versionCode;
}
public List<QuartzServiceModel> getListData() {
	return listData;
}
public void setListData(List<QuartzServiceModel> listData) {
	this.listData = listData;
}
}
