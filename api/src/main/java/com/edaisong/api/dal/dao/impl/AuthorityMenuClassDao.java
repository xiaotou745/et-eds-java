package com.edaisong.api.dal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IAuthorityMenuClassDao;
import com.edaisong.core.util.SqlSessionUtil;
import com.edaisong.entity.AuthorityMenuClass;
import com.edaisong.entity.MenuEntity;

@Repository
public class AuthorityMenuClassDao implements IAuthorityMenuClassDao {

	@Autowired
	private SqlSessionFactory superManReadOnlySqlServerSessionFactory;

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
	public int insertSelective(AuthorityMenuClass record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AuthorityMenuClass selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(AuthorityMenuClass record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(AuthorityMenuClass record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MenuEntity> getMenuListByUserID(String accountId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("accountId", accountId);
		List<MenuEntity> list = SqlSessionUtil
				.wapperSession(superManReadOnlySqlServerSessionFactory)
				.selectList(
						"com.edaisong.api.dal.dao.inter.IAuthorityAccountMenuSetDao.getMenuListByUserID",
						paramMap);
		return list;
	}

}
