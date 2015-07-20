package com.edaisong.api.dal.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.ITestUserTblDao;
import com.edaisong.entity.TestUserTbl;
import com.edaisong.entity.domain.testuser.TestUserRecord;

@Repository
public class TestUserTblDao implements ITestUserTblDao {
	//read database
	@Autowired
	private SqlSessionFactory superManReadOnlySqlServerSessionFactory;
	//write database
	@Autowired
	private SqlSessionFactory superManSqlServerSessionFactory;
	
	private final static String SELECT_ALL_TEST_USERS = "com.edaisong.api.dal.dao.inter.ITestUserTblDao.selectAllTestUsers";
	private final static String INSERT_STATEMENT = "com.edaisong.api.dal.dao.inter.ITestUserTblDao.insert";
	private final static String DELETE_STATEMENT = "com.edaisong.api.dal.dao.inter.ITestUserTblDao.deleteByPrimaryKey";
	private final static String SELECT_BY_PRIMARY_KEY_STATEMENT = "com.edaisong.api.dal.dao.inter.ITestUserTblDao.selectByPrimaryKey";
	private final static String UPDATE_BY_PRIMARY_KEY_STATEMENT = "com.edaisong.api.dal.dao.inter.ITestUserTblDao.updateByPrimaryKey";
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		SqlSession session = superManSqlServerSessionFactory.openSession();
		try{
			Map<String, Object> paramMap = new HashedMap();
			paramMap.put("id", id);
			int rowCount = session.delete(DELETE_STATEMENT,paramMap);
			session.commit();
			return rowCount;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		finally{
			session.close();
		}
	}

	@Override
	public int insert(String phoneNo){
		SqlSession session = superManSqlServerSessionFactory.openSession();
		try{
			Map<String, Object> paramMap = new HashedMap();
			paramMap.put("PhoneNo", phoneNo);
			int id = session.insert(INSERT_STATEMENT,paramMap);
			session.commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		finally{
			session.close();
		}
	}

	@Override
	public int insertSelective(TestUserTbl record) {
		// TODO Auto-generated method stub
		//return 0;
		throw new UnsupportedOperationException();
	}

	@Override
	public TestUserTbl selectByPrimaryKey(Integer id) {
		SqlSession session = superManReadOnlySqlServerSessionFactory.openSession();
		try{
			Map<String, Object> paramMap = new HashedMap();
			paramMap.put("id", id);
			return session.selectOne(SELECT_BY_PRIMARY_KEY_STATEMENT,paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			session.close();
		}
	}

	@Override
	public int updateByPrimaryKeySelective(TestUserTbl record) {
		// TODO Auto-generated method stub
		//return 0;
		throw new UnsupportedOperationException();
	}

	@Override
	public int updateByPrimaryKey(TestUserTbl record) {
		SqlSession session = superManSqlServerSessionFactory.openSession();
		try{
			Map<String, Object> paramMap = new HashedMap();
			paramMap.put("PhoneNo", record.getPhoneno());
			paramMap.put("id", record.getId());
			int id = session.insert(UPDATE_BY_PRIMARY_KEY_STATEMENT,paramMap);
			session.commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		finally{
			session.close();
		}
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
