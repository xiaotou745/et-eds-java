package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IBusinessGroupDao;
import com.edaisong.api.service.inter.IBusinessGroupService;
import com.edaisong.entity.BusinessGroup;

@Service
public class BuinessGroupService implements IBusinessGroupService {

	@Autowired
	private IBusinessGroupDao iBusinessGroupDao;
	
	@Override
	public List<BusinessGroup> getBusinessGroupList() {
		return iBusinessGroupDao.getBusinessGroupList();
	}

}
