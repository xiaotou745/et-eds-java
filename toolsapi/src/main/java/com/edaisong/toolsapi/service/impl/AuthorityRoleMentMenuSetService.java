package com.edaisong.toolsapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.toolsapi.dao.inter.IAccountDao;
import com.edaisong.toolsapi.dao.inter.IAuthorityRoleMentMenuSetDao;
import com.edaisong.toolsapi.redis.RedisService;
import com.edaisong.toolsapi.service.inter.IAuthorityRoleMentMenuSetService;
import com.edaisong.toolscore.consts.RedissCacheKey;
import com.edaisong.toolsentity.Account;
import com.edaisong.toolsentity.AuthorityRoleMentMenuSet;
@Service
public class AuthorityRoleMentMenuSetService implements IAuthorityRoleMentMenuSetService{

	@Autowired
	private IAuthorityRoleMentMenuSetDao authorityRoleMentMenuSetDao;
	@Autowired
	private IAccountDao accountDao;
	@Autowired
	private RedisService redisService;
	@Override
	public boolean modifyAuthList(List<AuthorityRoleMentMenuSet> authList) {
		if (authList!=null&&authList.size()>0) {
			boolean result= authorityRoleMentMenuSetDao.modifyAuthList(authList);
			//如果这个角色的权限更新成功，则将该角色下的所有用户的权限缓存移除
			if (result) {
				List<Account> roleAccounts=accountDao.getByRoleID(authList.get(0).getRoleid());
				for (Account account : roleAccounts) {
					String key=RedissCacheKey.Menu_Auth+account.getId();
					redisService.remove(key);
				}
			}
		}
		return false;
	}

}
