package com.edaisong.entity.req;


import java.lang.Double;
import java.util.Date;

import com.edaisong.entity.common.RequestBase;

public class ClienterBalanceRecordReq extends RequestBase{
	
	private Integer clienterId;
	private int currentPage;
	

    public Integer getClienterId() {
        return clienterId;
    }

    public void setClienterId(Integer clienterid) {
        this.clienterId = clienterid;
    }

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}	
	
	
	
}

