package com.edaisong.toolsapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.toolsapi.dao.inter.IAccountDao;
import com.edaisong.toolsapi.redis.RedisService;
import com.edaisong.toolsapi.service.inter.IAccountService;
import com.edaisong.toolscore.consts.RedissCacheKey;
import com.edaisong.toolscore.security.MD5Util;
import com.edaisong.toolsentity.Account;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.req.PagedAccountReq;
import com.edaisong.toolsentity.req.UpdatePwdReq;

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
	public int updatePwd(UpdatePwdReq req) {
		String oldPassword = MD5Util.MD5(req.getOldPwd());
		String newPassword = MD5Util.MD5(req.getNewPwd());
		req.setOldPwd(oldPassword);
		req.setNewPwd(newPassword);
		return accountDao.updatePwd(req);
	}

	@Override
	public int update(Account account) {
		return accountDao.update(account);
	}
}
