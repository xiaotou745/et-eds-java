package com.edaisong.toolsapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.toolsapi.dao.inter.IAccountLoginLogDao;
import com.edaisong.toolsapi.service.inter.IAccountLoginLogService;
import com.edaisong.toolsentity.AccountLog;

@Service
public class AccountLoginLogService implements IAccountLoginLogService{

	@Autowired
	private IAccountLoginLogDao logDao;
	@Override
	public void addLog(AccountLog log) {
		logDao.addLog(log);
	}

}
