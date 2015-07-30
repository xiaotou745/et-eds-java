package com.edaisong.api.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.IClienterDao;
import com.edaisong.api.service.inter.IClienterService;
import com.edaisong.entity.Account;
import com.edaisong.entity.Clienter;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.req.AccountReq;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.ClienterReq;
import com.edaisong.entity.resp.AccountResp;




@Service
public class ClienterService implements IClienterService {

	@Autowired
	private IClienterDao clienterDao;	

	@Override
	public int modifyStatusById(Clienter record) 
	{
		return clienterDao.updateByPrimaryKeySelective(record);
	}

	
	public int modifyMoneyById(ClienterOptionReq record)
	{
		return clienterDao.updateMoneyById(record);
	}   	

	
	@Override
	public PagedResponse<ClienterModel> query(ClienterReq req) {
		return  clienterDao.query(req);
	}
}
