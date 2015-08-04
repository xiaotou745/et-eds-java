package com.edaisong.entity.resp;

import com.edaisong.entity.common.ResponseBase;

public class BusinessLoginResp extends ResponseBase{
	private boolean loginSuccess;
	private int bid;

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public boolean isLoginSuccess() {
		return loginSuccess;
	}

	public void setLoginSuccess(boolean loginSuccess) {
		this.loginSuccess = loginSuccess;
	}
}
