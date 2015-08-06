package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.Business;
import com.edaisong.entity.BusinessExpressRelation;
import com.edaisong.entity.BusinessOptionLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessDetailModel;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.req.BusinessLoginReq;
import com.edaisong.entity.domain.BusinessModifyModel;
import com.edaisong.entity.req.PagedBusinessReq;
import com.edaisong.entity.resp.BusinessLoginResp;



public interface IBusinessService {
	PagedResponse<BusinessModel> getBusinessList(PagedBusinessReq req);
	
	/**
	 * 商家登录
	 * @param req
	 * @return
	 */
	public BusinessLoginResp login(BusinessLoginReq req);
	BusinessDetailModel getBusinessDetailByID(int businessID);
	List<BusinessOptionLog> getOpLogByBusinessID(int businessID);
	int modifyBusiness(BusinessModifyModel detailModel);
	void addLoginLog(String phoneNo, String description, boolean isSuccess);
	/**
	 * 根据商户Id获取商户信息
	 * @param businessId 商户Id
	 * @return Business
	 */
	Business getBusinessById(int businessId);
	/**
	 * 修改商家绑定的物流公司
	 * @param 
	 * @return 
	 */
	int modifyExpress(List<BusinessExpressRelation> listData);
}
