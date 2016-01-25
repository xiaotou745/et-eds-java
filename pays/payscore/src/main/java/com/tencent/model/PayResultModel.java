package com.tencent.model;

public class PayResultModel {
	private String qr_code;
	private String prepay_id;
	public String getQr_code() {
		return qr_code;
	}
	public void setQr_code(String qr_code) {
		this.qr_code = qr_code;
	}
	public String getPrepay_id() {
		return prepay_id;
	}
	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}
}
