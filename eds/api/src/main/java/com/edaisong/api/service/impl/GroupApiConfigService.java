package com.edaisong.api.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import com.edaisong.api.dao.inter.IGroupApiConfigDao;
import com.edaisong.api.service.inter.IGroupApiConfigService;
import com.edaisong.entity.GroupApiConfig;


@Service
public class GroupApiConfigService implements IGroupApiConfigService {

	@Autowired
	private IGroupApiConfigDao groupApiConfigDao;

	@Override
	public int add(GroupApiConfig record) {
		return groupApiConfigDao.insert(record);
	}
   
}
