package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IAccountDao;
import com.edaisong.api.service.inter.IAccountService;
import com.edaisong.entity.Account;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.AccountReq;
import com.edaisong.entity.resp.AccountResp;

@Service
public class AccountService implements IAccountService{

	@Autowired
	private IAccountDao accountDao;
	
	@Override
	public PagedResponse<Account> queryAccount(AccountReq req) {
		return  accountDao.query(req);
	}
}
