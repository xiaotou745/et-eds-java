package com.edaisong.entity.domain;

/**
 *
 * @author pengyi
 * @date 2015年9月7日 下午1:21:36
 * @version 1.0
 * @parameter
 * @since
 */
public class BusinessBindClienter {
	// 行号
	private int rowCount;
	// 名称
	private String clienterName;
	// 骑士电话
	private String clienterPhoneNo;
	// 上传备注
	private String clienterRemarks;
	// 是否成功
	private boolean isEnable;
	// 是否绑定
	private boolean isBind;
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public String getClienterName() {
		return clienterName;
	}
	public void setClienterName(String clienterName) {
		this.clienterName = clienterName;
	}
	public String getClienterPhoneNo() {
		return clienterPhoneNo;
	}
	public void setClienterPhoneNo(String clienterPhoneNo) {
		this.clienterPhoneNo = clienterPhoneNo;
	}
	public String getClienterRemarks() {
		return clienterRemarks;
	}
	public void setClienterRemarks(String clienterRemarks) {
		this.clienterRemarks = clienterRemarks;
	}
	public boolean isEnable() {
		return isEnable;
	}
	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}
	public boolean isBind() {
		return isBind;
	}
	public void setBind(boolean isBind) {
		this.isBind = isBind;
	}
}
