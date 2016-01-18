package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IAccountDao;
import com.edaisong.api.redis.RedisService;
import com.edaisong.api.service.inter.IAccountService;
import com.edaisong.core.consts.RedissCacheKey;
import com.edaisong.core.security.MD5Util;
import com.edaisong.entity.Account;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedAccountReq;
import com.edaisong.entity.req.UpdatePwdReq;

@Service
public class AccountService implements IAccountService{

	@Autowired
	private IAccountDao accountDao;
	@Autowired
	private RedisService redisService;
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
		int result= accountDao.updateRoleID(userID,newRoleID);
		if (result>0) {
			String key=RedissCacheKey.Menu_Auth+userID;
			redisService.remove(key);
		}
		return result;
	}

	@Override
	public List<Account> getByRoleID(int roleID) {
		return accountDao.getByRoleID(roleID);
	}

	@Override
	public int insert(Account account) {
		return accountDao.insert(account);
	}

	@Override
	public int update(Account account) {
		return accountDao.update(account);
	}

	@Override
	public int updatePwd(UpdatePwdReq req) {
		String oldPassword = MD5Util.MD5(req.getOldPwd());
		String newPassword = MD5Util.MD5(req.getNewPwd());
		req.setOldPwd(oldPassword);
		req.setNewPwd(newPassword);
		return accountDao.updatePwd(req);
	}
}
