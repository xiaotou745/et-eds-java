package com.edaisong.api.service.inter;

import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessModel;

import com.edaisong.entity.req.PagedBusinessReq;



public interface IBusinessService {
	PagedResponse<BusinessModel> getBusinessList(PagedBusinessReq req);
}
