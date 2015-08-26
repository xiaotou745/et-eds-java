package com.edaisong.api.service.impl;

import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IAccountLoginLogDao;
import com.edaisong.api.service.inter.IAccountLoginLogService;
import com.edaisong.entity.AccountLog;

@Service
public class AccountLoginLogService implements IAccountLoginLogService{

	private IAccountLoginLogDao logDao;
	@Override
	public void addLog(AccountLog log) {
		logDao.addLog(log);
	}

}
