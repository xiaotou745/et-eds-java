package com.edaisong.api.dal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IAccountDao;
import com.edaisong.core.util.SqlSessionUtil;
import com.edaisong.entity.Account;
import com.edaisong.entity.MenuEntity;

@Repository
public class AccountDao implements IAccountDao {
	@Autowired
	private SqlSessionFactory superManReadOnlySqlServerSessionFactory;

	// 查询所有管理后台用户列表
	@Override
	public List<Account> query() {
		Map<String, Object> map = new HashMap<String, Object>();

		List<Account> list = SqlSessionUtil.wapperSession(
				superManReadOnlySqlServerSessionFactory).selectList(
				"com.edaisong.api.dal.dao.inter.IAccountDao.query", map);
		return list;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Account record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Account record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Account selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Account record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Account record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
