package com.edaisong.entity.resp;

import com.edaisong.entity.common.ResponseBase;

public class BusinessLoginResp extends ResponseBase{
	private boolean loginSuccess;

	public boolean isLoginSuccess() {
		return loginSuccess;
	}

	public void setLoginSuccess(boolean loginSuccess) {
		this.loginSuccess = loginSuccess;
	}
}
