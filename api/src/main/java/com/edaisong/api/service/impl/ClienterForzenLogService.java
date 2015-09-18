package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IClienterForzenLogDao;
import com.edaisong.api.service.inter.IClienterForzenLogService;
import com.edaisong.entity.ClienterForzenLog;

@Service
public class ClienterForzenLogService implements IClienterForzenLogService {

	@Autowired
	private IClienterForzenLogDao clienterForzenLogDao;
	@Override
	public List<ClienterForzenLog> getList(int forzenId) { 
		return clienterForzenLogDao.getList(forzenId);
	}

}
