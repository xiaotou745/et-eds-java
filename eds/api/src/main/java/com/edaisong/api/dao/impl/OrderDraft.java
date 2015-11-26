package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IAccountDao;
import com.edaisong.api.dao.inter.IOrderDraftDao;
import com.edaisong.entity.Account;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedAccountReq;

@Repository
public class OrderDraft extends DaoBase implements IOrderDraftDao {

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(com.edaisong.entity.OrderDraft record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(com.edaisong.entity.OrderDraft record) {
		return getMasterSqlSessionUtil().insert(
				"com.edaisong.api.dao.inter.IOrderDraftDao.insertSelective", record);	
	}

	@Override
	public com.edaisong.entity.OrderDraft selectByPrimaryKey(Integer id) {
		return getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IOrderDraftDao.selectByPrimaryKey", id);	
	}

	@Override
	public int updateByPrimaryKeySelective(com.edaisong.entity.OrderDraft record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(com.edaisong.entity.OrderDraft record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
