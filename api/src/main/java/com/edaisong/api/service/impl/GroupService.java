package com.edaisong.api.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.edaisong.api.dal.dao.inter.IGroupDao;
import com.edaisong.api.service.inter.IGroupService;
import com.edaisong.entity.Group;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.req.GroupReq;
@Service
public class GroupService implements IGroupService {

	@Autowired
	private IGroupDao dao;
	@Override
	public List<GroupModel> getGroupListByID(GroupReq req) {
		return dao.getGroupListByID(
				req.getId());
	}

	@Override
	public List<GroupModel> getGroupList(GroupReq req) {
		return dao.getGroupList(
				req);
	}

	@Override
	public int add(Group record) {
		return dao.insert(record);
	}
	
	@Override
	public int  update(Group record) 
	{
		return dao.updateByPrimaryKeySelective(record);
	}

   
}
