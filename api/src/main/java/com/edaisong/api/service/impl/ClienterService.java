package com.edaisong.api.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.IClienterDao;
import com.edaisong.api.service.inter.IClienterService;
import com.edaisong.entity.Clienter;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.req.AccountReq;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.ClienterReq;
import com.edaisong.entity.resp.AccountResp;
import com.edaisong.entity.resp.ClienterResp;



@Service
public class ClienterService implements IClienterService {

	@Autowired
	private IClienterDao clienterDao;	

	@Override
	public int modifyStatusById(Clienter record) 
	{
		return clienterDao.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public ClienterResp getClienterList(ClienterReq record) {
		// TODO Auto-generated method stub
		ClienterResp resp = new ClienterResp();
		List<ClienterModel> listData = clienterDao.getClienterList(
				record);
		resp.setClienterList(listData);
		return resp;
	}
	
	public int modifyMoneyById(ClienterOptionReq record)
	{
		return clienterDao.updateMoneyById(record);
	}
   
	
	public ClienterResp queryClienter(ClienterReq req)
	{
		return clienterDao.query(req);
	}
}
