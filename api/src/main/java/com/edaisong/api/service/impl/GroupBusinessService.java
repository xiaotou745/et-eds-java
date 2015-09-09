package com.edaisong.api.service.impl;

import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IGroupBusinessService;
import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedGroupBusinessReq;
 

@Service
public class GroupBusinessService implements IGroupBusinessService {

	@Override
	public PagedResponse<GroupBusiness> getBusinessList(
			PagedGroupBusinessReq req) {
		// TODO Auto-generated method stub
		return null;
	} 
}
