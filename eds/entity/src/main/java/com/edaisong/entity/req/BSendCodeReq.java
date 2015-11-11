package com.edaisong.entity.req;

/**
 * B端发送短信验证码
 * @author CaoHeYang
 * @date 20151110
 */
public class BSendCodeReq {
	private String phoneNo;//账号
	private int type;
	private int messageType;
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * 验证码类型 0 短信 1语音 默认0
	 * @return
	 */
	public int getMessageType() {
		return messageType;
	}
	/**
	 * 验证码类型 0 短信 1语音 默认0
	 * @return
	 */
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	
}
