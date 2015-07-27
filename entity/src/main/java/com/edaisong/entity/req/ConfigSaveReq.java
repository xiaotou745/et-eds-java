package com.edaisong.entity.req;

import com.edaisong.entity.common.RequestBase;

public class ConfigSaveReq extends RequestBase{
	private String id;
	private String configValue;
	public String getId() {
		return id;
	}
	/*
	 * 配置ID
	 * */
	public void setId(String id) {
		this.id = id;
	}
	public String getConfigValue() {
		return configValue;
	}
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
}
