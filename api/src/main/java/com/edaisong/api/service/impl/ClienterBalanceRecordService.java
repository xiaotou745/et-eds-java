package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.IAccountDao;
import com.edaisong.api.dal.dao.inter.IClienterBalanceRecordDao;
import com.edaisong.api.service.inter.IAccountService;
import com.edaisong.api.service.inter.IClienterBalanceRecordService;
import com.edaisong.entity.Account;
import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.req.AccountReq;
import com.edaisong.entity.req.ClienterBalanceRecordReq;
import com.edaisong.entity.req.ClienterReq;
import com.edaisong.entity.resp.AccountResp;

@Service
public class ClienterBalanceRecordService implements IClienterBalanceRecordService{

	@Autowired
	private IClienterBalanceRecordDao clienterBalanceRecordDao;


	@Override
	public  PagedResponse<ClienterBalanceRecord>  query(ClienterBalanceRecordReq req)
	{
		return  clienterBalanceRecordDao.query(req);
	}	

}
