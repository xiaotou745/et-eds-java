package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IClienterBindOptionLogDao;
import com.edaisong.api.service.inter.IClienterBindOptionLogService;
import com.edaisong.entity.ClienterBindOptionLog;
@Service
public class ClienterBindOptionLogService implements IClienterBindOptionLogService{
@Autowired
private IClienterBindOptionLogDao clienterBindOptionLogDao;
	@Override
	public int insert(ClienterBindOptionLog record) {
return clienterBindOptionLogDao.insert(record);
	}

	@Override
	public List<ClienterBindOptionLog> selectList(Long businessId,
			long clienterId) {
return clienterBindOptionLogDao.selectList(businessId, clienterId);
	}

}
