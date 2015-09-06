package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IAuthorityAccountMenuSetDao;
import com.edaisong.api.service.inter.IAuthorityAccountMenuSetService;
import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.consts.RedissCacheKey;
import com.edaisong.entity.AuthorityAccountMenuSet;
import com.edaisong.entity.MenuEntity;

@Service
public class AuthorityAccountMenuSetService implements IAuthorityAccountMenuSetService{
@Autowired
	private RedisService redisService;
	@Autowired
	private IAuthorityAccountMenuSetDao authorityAccountMenuSetDao;
	@Override
	public List<Integer> getMenuIdsByAccountId(Integer id) {
		return authorityAccountMenuSetDao.getMenuIdsByAccountId(id);
	}
	@Override
	public boolean modifyAuthList(List<AuthorityAccountMenuSet> authList) {
		if (authList==null||authList.size()==0) {
			return true;
		}
		boolean result=authorityAccountMenuSetDao.modifyAuthList(authList);
		if (result) {
			String key=RedissCacheKey.Menu_Auth+authList.get(0).getAccoutid();
			redisService.remove(key);
		}
		return result;
	}
}
