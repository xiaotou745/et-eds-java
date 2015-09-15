package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edaisong.api.dao.inter.IGroupBusinessLogDao;
import com.edaisong.api.service.inter.IGroupBusinessLogService;
import com.edaisong.entity.GroupBusinessLog;

@Service
public class GroupBusinessLogService implements IGroupBusinessLogService {
	@Autowired
	private IGroupBusinessLogDao dao;
	
	@Override
	public List<GroupBusinessLog> getList(int id) {
		return dao.getList(id);
	}

	@Override
	public int insert(GroupBusinessLog gbl) {
		return dao.insert(gbl);
	}

}
