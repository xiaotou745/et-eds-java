package com.edaisong.toolsapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.toolsentity.AccountInfo;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.domain.UpdatePwdReq;
import com.edaisong.toolsentity.req.PagedAccountInfoReq; 
import com.edaisong.toolsapi.dao.inter.IAccountInfoDao;
import com.edaisong.toolsapi.redis.RedisService; 
import com.edaisong.toolsapi.service.inter.IAccountInfoService; 
import com.edaisong.toolscore.consts.RedissCacheKey;
import com.edaisong.toolscore.security.MD5Util; 


@Service
public class AccountInfoService implements IAccountInfoService{

	@Autowired
	private IAccountInfoDao accountInfoDao;
	@Autowired
	private RedisService redisService;
	@Override
	public PagedResponse<AccountInfo> queryAccount(PagedAccountInfoReq req) {
		return  accountInfoDao.queryAccount(req);
	}

	@Override
	public AccountInfo login(String username, String password) {
		password = MD5Util.MD5(password);
		return accountInfoDao.login(username, password);
	}

	@Override
	public AccountInfo getByID(int userID) {
		return accountInfoDao.getByID(userID);
	}

	@Override
	public int updateRoleID(int userID, int newRoleID) {
		int result= accountInfoDao.updateRoleID(userID,newRoleID);
		if (result>0) {
			String key=RedissCacheKey.Menu_Auth+userID;
			redisService.remove(key);
		}
		return result;
	}

	@Override
	public List<AccountInfo> getByRoleID(int roleID) {
		return accountInfoDao.getByRoleID(roleID);
	}

	@Override
	public int insert(AccountInfo account) {
		return accountInfoDao.insert(account);
	}

	@Override
	public int update(AccountInfo account) {
		return accountInfoDao.update(account);
	}

	@Override
	public int updatePwd(UpdatePwdReq req) {
		String oldPassword = MD5Util.MD5(req.getOldPwd());
		String newPassword = MD5Util.MD5(req.getNewPwd());
		req.setOldPwd(oldPassword);
		req.setNewPwd(newPassword);
		return accountInfoDao.updatePwd(req);
	}
}
