package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IAccountDao;
import com.edaisong.api.service.inter.IAccountService;
import com.edaisong.core.security.MD5Util;
import com.edaisong.entity.Account;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedAccountReq;

@Service
public class AccountService implements IAccountService{

	@Autowired
	private IAccountDao accountDao;
	
	@Override
	public PagedResponse<Account> queryAccount(PagedAccountReq req) {
		return  accountDao.query(req);
	}

	@Override
	public Account login(String username, String password) {
		password = MD5Util.MD5(password);
		return accountDao.login(username, password);
	}

	@Override
	public Account getByID(int userID) {
		return accountDao.getByID(userID);
	}

	@Override
	public int updateRoleID(int userID, int newRoleID) {
		return accountDao.updateRoleID(userID,newRoleID);
	}
}
