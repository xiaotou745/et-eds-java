package com.edaisong.api.service.inter;

import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessClienterRelationModel;
import com.edaisong.entity.req.ClienterBindOptionReq;
import com.edaisong.entity.req.PagedCustomerSearchReq;

public interface IBusinessClienterRelationService {
	int getBusinessBindClienterQty(int businessId);
	
	PagedResponse<BusinessClienterRelationModel> getBusinessClienterRelationList(PagedCustomerSearchReq req);
	
	boolean modifyClienterBind(ClienterBindOptionReq req);
	
	boolean removeclienterbind(ClienterBindOptionReq req);
}
