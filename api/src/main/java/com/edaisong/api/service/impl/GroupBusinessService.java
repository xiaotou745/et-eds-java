package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IGroupBusinessDao;
import com.edaisong.api.dao.inter.IGroupDao;
import com.edaisong.api.service.inter.IGroupBusinessService;
import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessModel;
import com.edaisong.entity.req.GroupBusinessReq;
import com.edaisong.entity.req.PagedGroupBusinessReq;
 

@Service
public class GroupBusinessService implements IGroupBusinessService {

	@Autowired
	private IGroupBusinessDao dao;
	
	@Override
	public PagedResponse<GroupBusinessModel> getPageList(
			PagedGroupBusinessReq req) {
		// TODO Auto-generated method stub
		return dao.getPageList(req);
	}

	@Override
	public GroupBusinessModel getSingle(GroupBusinessReq gbr) {
		// TODO Auto-generated method stub
		return dao.getSingle(gbr);
	}

	@Override
	public int addGroupBusiness(GroupBusiness groupBusiness) {
		// TODO Auto-generated method stub
		return dao.addGroupBusiness(groupBusiness);
	}

	@Override
	public int modifyGroupBusiness(GroupBusiness groupBusiness) {
		return dao.modifyGroupBusiness(groupBusiness);
	}

	 
}
