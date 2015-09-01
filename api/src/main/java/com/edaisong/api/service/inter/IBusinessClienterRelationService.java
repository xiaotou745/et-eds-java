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
	
	/**
	 * 确实骑士是否已绑定商家
	 * @author pengyi
	 * @date 20150901
	 */
	boolean checkHaveBind(ClienterBindOptionReq req);
	
	/**
	 * 添加骑士绑定
	 * @author pengyi
	 * @date 20150901
	 * @param req
	 * @return
	 */
	boolean addClienterBind(ClienterBindOptionReq req);
}
