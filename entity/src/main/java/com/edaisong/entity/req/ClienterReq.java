package com.edaisong.entity.req;


import java.math.BigDecimal;
import java.util.Date;

import com.edaisong.entity.common.RequestBase;

public class ClienterReq extends RequestBase{
	
	private String clienterName;
	private String clienterPhoneNo;
	
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
}

