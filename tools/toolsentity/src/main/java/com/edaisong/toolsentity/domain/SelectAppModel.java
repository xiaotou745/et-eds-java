package com.edaisong.toolsentity.domain;

public class SelectAppModel {
	private String appName;
	private String dbName;
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getDbName() {
		return dbName.toLowerCase();
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
}
