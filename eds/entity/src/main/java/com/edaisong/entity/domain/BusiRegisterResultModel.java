package com.edaisong.entity.domain;

public class BusiRegisterResultModel {
	 /// <summary>
    /// 用户的Id
    /// </summary>
    private int userId;

    /// <summary>
    /// 唯一健值guid
    /// </summary>
    private String appkey;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

    /// <summary>
    /// Tokey值
    /// </summary>
    //private string token;
}
