package com.edaisong.entity.req;

/**
 * B端发送短信验证码
 * @author CaoHeYang
 * @date 20151110
 */
public class BSendCodeReq {
	private String phoneNo;//账号
	private int sType;
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public int getsType() {
		return sType;
	}
	public void setsType(int sType) {
		this.sType = sType;
	}
}
