package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IGroupBusinessDao;
import com.edaisong.api.service.inter.IGroupBusinessService;
import com.edaisong.entity.Business;
import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseCode;
import com.edaisong.entity.req.PagedGroupBusinessReq;
import com.edaisong.entity.resp.BusinessLoginResp;
 

@Service
public class GroupBusinessService implements IGroupBusinessService {
@Autowired 
IGroupBusinessDao groupBusinessDao;
	@Override
	public PagedResponse<GroupBusiness> getBusinessList(PagedGroupBusinessReq req) {
			return groupBusinessDao.getBusinessList(req);
	}

	@Override
	public GroupBusiness login(String phoneNo, String password) {
		return groupBusinessDao.getByPhoneNoAndPwd(phoneNo, password);
	} 
}
