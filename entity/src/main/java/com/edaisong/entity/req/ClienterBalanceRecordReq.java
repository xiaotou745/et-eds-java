<<<<<<< HEAD
package com.edaisong.entity.req;


import java.math.BigDecimal;
import java.util.Date;

import com.edaisong.entity.common.PagedRequestBase;
import com.edaisong.entity.common.RequestBase;

public class ClienterBalanceRecordReq extends PagedRequestBase{
	
	public Integer getClienterId() {
		return clienterId;
	}
	public void setClienterId(Integer clienterId) {
		this.clienterId = clienterId;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	private Integer clienterId;
	private int businessId;
}

=======
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

>>>>>>> 772aaa3d48304344b9aa3741b8664c84a74d3a86
