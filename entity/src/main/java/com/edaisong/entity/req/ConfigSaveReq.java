package com.edaisong.entity.req;

import com.edaisong.entity.common.RequestBase;

public class ConfigSaveReq extends RequestBase{
	private Integer id;
	private String keyName;
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	private String configValue;
	public Integer getId() {
		return id;
	}
	/*
	 * 配置ID
	 * */
	public void setId(Integer id) {
		this.id = id;
	}
	public String getConfigValue() {
		return configValue;
	}
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
}
