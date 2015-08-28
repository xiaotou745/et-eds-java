package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IAccountDao;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.entity.Account;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedAccountReq;
import com.edaisong.entity.resp.AccountResp;

@Repository
public class AccountDao extends DaoBase implements IAccountDao {
	// 查询所有管理后台用户列表
	@Override
	public PagedResponse<Account> query(PagedAccountReq req) {
		return getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dao.inter.IAccountDao.query", req);
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

	@Override
	public Account login(String username, String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("password", password);
		return getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IAccountDao.login", params);
	}

}
