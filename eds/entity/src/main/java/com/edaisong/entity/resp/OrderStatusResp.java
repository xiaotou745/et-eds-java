package com.edaisong.entity.resp;

import java.util.Date;

import com.edaisong.entity.common.ResponseBase;

/**
 * 商户发布订单
 * @author 胡灵波
 * @Date 2015年8月6日 09:48:17
 */
public class OrderStatusResp extends ResponseBase{	
	private Byte status;
	private String  statusStr;

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}
}
