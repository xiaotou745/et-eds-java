package com.edaisong.toolsentity.domain;

/**
 * 骑士绑定信息
 * @author  pengyi 
 * @date 2015年9月2日 下午5:19:04
 * @version 1.0
 * @parameter
 * @since
 */
public class ClienterBindInfoModel {
	private int id;
	private String phoneNo;
	private String trueName;
	private int isBind;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public int getIsBind() {
		return isBind;
	}
	public void setIsBind(int isBind) {
		this.isBind = isBind;
	}
}
