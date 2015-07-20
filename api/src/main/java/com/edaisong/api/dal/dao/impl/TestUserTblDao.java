package com.edaisong.api.dal.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.edaisong.api.dal.dao.inter.ITestUserTblDao;
import com.edaisong.entity.TestUserTbl;
import com.edaisong.entity.domain.testuser.TestUserRecord;

public class TestUserTblDao implements ITestUserTblDao {
	@Autowired
	private SqlSessionFactory superManReadOnlySqlServerSessionFactory;
	private final static String SELECT_ALL_TEST_USERS = "com.edaisong.api.dal.dao.inter.ITestUserTblDao.selectAllTestUsers";
	
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
		SqlSession session = superManReadOnlySqlServerSessionFactory.openSession();
		try{
			List<TestUserRecord> list = session.selectList(SELECT_ALL_TEST_USERS);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			session.close();
		}
	}

}
