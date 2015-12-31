package com.edaisong.entity.req;

import java.util.Date;

import com.edaisong.entity.common.PagedRequestBase;


public class PagedOrderTipReq extends PagedRequestBase{	 	
	    
	    private String amount;

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

	
}
