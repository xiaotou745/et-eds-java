package com.edaisong.entity.req;


import java.math.BigDecimal;
import java.util.Date;

import com.edaisong.entity.common.RequestBase;

public class ClienterBalanceRecordReq extends RequestBase{

	private int currentPage;

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}	
	
}

