package com.edaisong.api.dal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IAccountDao;
import com.edaisong.entity.Account;


@Repository
public class AccountDao extends DaoBase implements IAccountDao {
	// 查询所有管理后台用户列表
	@Override
	public List<Account> query() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Account> list = getReadOnlySqlSessionUtil().selectList(
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
