package com.edaisong.api.dal.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.edaisong.api.dal.dao.inter.ITestUserTblDao;
import com.edaisong.entity.TestUserTbl;
import com.edaisong.entity.domain.testuser.TestUserRecord;

public class TestUserTblDao implements ITestUserTblDao {
	@Autowired
	private SqlSessionFactory superManReadOnlySqlServerSessionFactory;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(TestUserTbl record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(TestUserTbl record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TestUserTbl selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(TestUserTbl record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(TestUserTbl record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TestUserRecord> selectAllTestUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
