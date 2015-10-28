package com.edaisong.toolsapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.toolsapi.dao.inter.IAccountDao;
import com.edaisong.toolsapi.dao.inter.IAuthorityRoleDao;
import com.edaisong.toolsapi.redis.RedisService;
import com.edaisong.toolsapi.service.inter.IAuthorityRoleService;
import com.edaisong.toolscore.consts.RedissCacheKey;
import com.edaisong.toolsentity.Account;
import com.edaisong.toolsentity.AuthorityRole;
@Service
public class AuthorityRoleService implements IAuthorityRoleService {
	@Autowired
	private IAuthorityRoleDao authorityRoleDao;
	@Autowired
	private IAccountDao accountDao;
	@Autowired
	private RedisService redisService;
	@Override
	public int insert(AuthorityRole record) {
		return authorityRoleDao.insert(record);
	}

	@Override
	public int update(AuthorityRole record) {
		int result= authorityRoleDao.update(record);
		//如果这个角色信息更新成功，则将该角色下的所有用户的权限缓存移除
		if (result>0) {
			List<Account> roleAccounts=accountDao.getByRoleID(record.getId());
			for (Account account : roleAccounts) {
				String key=RedissCacheKey.Menu_Auth+account.getId();
				redisService.remove(key);
			}
		}
		return result;
	}

	@Override
	public List<AuthorityRole> selectList() {
		return authorityRoleDao.selectList();
	}

}
