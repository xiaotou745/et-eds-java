package com.edaisong.toolsapi.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edaisong.toolsapi.common.DaoBase;
import com.edaisong.toolsapi.dao.inter.IAuthorityMenuClassDao;
import com.edaisong.toolsapi.redis.RedisService;
import com.edaisong.toolscore.consts.RedissCacheKey;
import com.edaisong.toolsentity.AuthorityMenuClass;
import com.edaisong.toolsentity.MenuEntity;

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
		List<MenuEntity> result=redisService.get(key, List.class);
		result=null;
		if (result==null||result.size()==0) {
			List<MenuEntity> list = getReadOnlySqlSessionUtil()
					.selectList(
							"com.edaisong.toolsapi.dao.inter.IAuthorityMenuClassDao.getMenuListByUserID",
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
						"com.edaisong.toolsapi.dao.inter.IAuthorityMenuClassDao.getAuthSettingList",
						userID);
	}

	@Override
	public List<MenuEntity> getRoleAuthSettingList(int roleID) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"com.edaisong.toolsapi.dao.inter.IAuthorityMenuClassDao.getRoleAuthSettingList",
						roleID);
	}

	@Override
	public List<AuthorityMenuClass> getListMenuByParId(int parId) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"com.edaisong.toolsapi.dao.inter.IAuthorityMenuClassDao.getListMenuByParId",
						parId);
	}

	@Override
	public AuthorityMenuClass getMenuById(int id) {
		return getReadOnlySqlSessionUtil()
				.selectOne(
						"com.edaisong.toolsapi.dao.inter.IAuthorityMenuClassDao.getMenuById",
						id);
	}

	@Override
	public boolean addMenu(AuthorityMenuClass req) {
		return getMasterSqlSessionUtil()
				.insert(
						"com.edaisong.toolsapi.dao.inter.IAuthorityMenuClassDao.addMenu",
						req) > 0;
	}


}
