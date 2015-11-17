package com.edaisong.toolsapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.toolsapi.dao.inter.IAccountDao;
import com.edaisong.toolsapi.dao.inter.IRoleInfoDao;
import com.edaisong.toolsapi.redis.RedisService;
import com.edaisong.toolsapi.service.inter.IRoleInfoService; 
import com.edaisong.toolscore.consts.RedissCacheKey;
import com.edaisong.toolsentity.Account;
import com.edaisong.toolsentity.RoleInfo; 

@Service
public class RoleInfoService implements IRoleInfoService {
	@Autowired
	private IRoleInfoDao roleInfoDao;
	@Autowired
	private IAccountDao accountInfoDao;
	@Autowired
	private RedisService redisService;
	@Override
	public int insert(RoleInfo record) {
		return roleInfoDao.insert(record);
	}

	@Override
	public int update(RoleInfo record) {
		int result= roleInfoDao.update(record);
		//如果这个角色信息更新成功，则将该角色下的所有用户的权限缓存移除
		if (result>0) {
			List<Account> roleAccounts=accountInfoDao.getByRoleID(record.getId());
			for (Account account : roleAccounts) {
				String key=RedissCacheKey.Menu_Auth+account.getId();
				redisService.remove(key);
			}
		}
		return result;
	}

	@Override
	public List<RoleInfo> selectList() {
		return roleInfoDao.selectList();
	}

}
