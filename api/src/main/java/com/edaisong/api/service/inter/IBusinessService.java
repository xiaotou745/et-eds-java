package com.edaisong.api.service.inter;

import com.edaisong.entity.common.ResponsePageList;
import com.edaisong.entity.domain.BusinessModel;

import com.edaisong.entity.req.BusinessReq;



public interface IBusinessService {
	ResponsePageList<BusinessModel> getBusinessList(BusinessReq req);
}
