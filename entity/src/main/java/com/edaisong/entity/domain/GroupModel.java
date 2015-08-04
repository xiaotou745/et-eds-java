package com.edaisong.entity.domain;

import java.util.Date;

import com.edaisong.entity.Group;

public class GroupModel extends Group{
    private String appKey;

    private String appSecret;

    private String appVersion;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
}
