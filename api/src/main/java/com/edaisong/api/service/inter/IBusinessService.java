package com.edaisong.api.service.inter;

import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.req.BusinessLoginReq;
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
}
