package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.Business;
import com.edaisong.entity.req.BusinessReq;
import com.edaisong.entity.resp.BusinessResp;


public interface IBusinessService {
	BusinessResp GetBusinessList(BusinessReq req);
}
