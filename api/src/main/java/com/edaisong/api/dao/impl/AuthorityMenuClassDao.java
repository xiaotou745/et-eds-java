package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IAuthorityMenuClassDao;
import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.consts.RedissCacheKey;
import com.edaisong.entity.AuthorityAccountMenuSet;
import com.edaisong.entity.AuthorityMenuClass;
import com.edaisong.entity.MenuEntity;

@Repository
public class AuthorityMenuClassDao extends DaoBase implements
		IAuthorityMenuClassDao {
	@Autowired
	private RedisService redisService;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(AuthorityMenuClass record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MenuEntity> getMenuListByUserID(int accountId) {
		String key=RedissCacheKey.Menu_Auth+accountId;
		List<MenuEntity> result=null;//redisService.get(key, List.class); 茹化肖暂时注释
		if (result==null||result.size()==0) {
			List<MenuEntity> list = getReadOnlySqlSessionUtil()
					.selectList(
							"com.edaisong.api.dao.inter.IAuthorityMenuClassDao.getMenuListByUserID",
							accountId);
			redisService.set(key, list);
			return list;
		}

		return result;
	}

	@Override
	public List<MenuEntity> getAuthSettingList(int userID) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"com.edaisong.api.dao.inter.IAuthorityMenuClassDao.getAuthSettingList",
						userID);
	}

	@Override
	public List<MenuEntity> getRoleAuthSettingList(int roleID) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"com.edaisong.api.dao.inter.IAuthorityMenuClassDao.getRoleAuthSettingList",
						roleID);
	}



}
