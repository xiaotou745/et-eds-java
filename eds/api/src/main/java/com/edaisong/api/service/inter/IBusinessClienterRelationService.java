package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.BusinessClienterRelation;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessClienterRelationModel;
import com.edaisong.entity.domain.ServiceClienters;
import com.edaisong.entity.req.ClienterBindOptionReq;
import com.edaisong.entity.req.OptBindClienterReq;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedGetMyServiceClientersReq;
import com.edaisong.entity.resp.GetMyServiceClientersResp;

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
	
	BusinessClienterRelation getDetails(int businessId,int clienterId);
	
	/**
	 * 商戶端 我的骑士
	 * @version 20151103
	 * @author CaoHeYang
	 * @date 20151103
	 * @param req
	 * @return
	 */
	GetMyServiceClientersResp   getMyServiceClienters(PagedGetMyServiceClientersReq req);

	/**
	 * 商戶端 我的骑士 申请中 同意/拒绝功能
	 * 
	 * @version 20151103
	 * @author CaoHeYang
	 * @date 20151103
	 * @param req
	 * @return
	 */
		int optBindClienter(OptBindClienterReq req);
	  
}
