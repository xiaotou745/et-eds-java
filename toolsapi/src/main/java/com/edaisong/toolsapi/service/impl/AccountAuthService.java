package com.edaisong.toolsapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.toolsapi.dao.inter.IAccountAuthDao;
import com.edaisong.toolsapi.redis.RedisService;
import com.edaisong.toolsapi.service.inter.IAccountAuthService; 
import com.edaisong.toolscore.consts.RedissCacheKey;
import com.edaisong.toolsentity.AccountAuthority; 


@Service
public class AccountAuthService implements IAccountAuthService{
@Autowired
	private RedisService redisService;
	@Autowired
	private IAccountAuthDao accountAuthDao;
	@Override
	public List<Integer> getMenuIdsByAccountId(Integer id) {
		return accountAuthDao.getMenuIdsByAccountId(id);
	}
	@Override
	public boolean modifyAuthList(List<AccountAuthority> authList) {
		if (authList==null||authList.size()==0) {
			return true;
		}
		boolean result=accountAuthDao.modifyAuthList(authList);
		if (result) {
			String key=RedissCacheKey.Menu_Auth+authList.get(0).getAccountid();
			redisService.remove(key);
		}
		return result;
	}
}
