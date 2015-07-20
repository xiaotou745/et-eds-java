package com.edaisong.api.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.edaisong.api.dal.dao.inter.IGroupDao;
import com.edaisong.api.service.inter.IGroupService;
import com.edaisong.entity.Group;
import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.resp.GroupResp;

@Service
public class GroupService implements IGroupService {
	@Autowired
	private IGroupDao dao;
	@Override
	public GroupResp  getGroupListByID(GroupReq req) {
		GroupResp resp = new GroupResp();
		List<Group> listData = dao.getGroupListByID(
				req.getId());
		resp.setGroupList(listData);
		return resp;
	}

}
