package com.edaisong.entity.resp;

import com.edaisong.entity.Business;
import com.edaisong.entity.common.ResponseBase;

public class BusinessLoginResp extends ResponseBase{
	private Business business;
	
	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}
}
