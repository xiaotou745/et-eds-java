package com.edaisong.api.dal.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.ITestUserTblDao;
import com.edaisong.core.util.SqlSessionUtil;
import com.edaisong.entity.TestUserTbl;
import com.edaisong.entity.domain.TestUserRecord;

@Repository
public class TestUserTblDao implements ITestUserTblDao {
	// read database
	@Autowired
	private SqlSessionFactory superManReadOnlySqlServerSessionFactory;
	// write database
	@Autowired
	private SqlSessionFactory superManSqlServerSessionFactory;

	private final static String SELECT_ALL_TEST_USERS = "com.edaisong.api.dal.dao.inter.ITestUserTblDao.selectAllTestUsers";
	private final static String INSERT_STATEMENT = "com.edaisong.api.dal.dao.inter.ITestUserTblDao.insert";
	private final static String DELETE_STATEMENT = "com.edaisong.api.dal.dao.inter.ITestUserTblDao.deleteByPhoneNo";
	private final static String SELECT_BY_PRIMARY_KEY_STATEMENT = "com.edaisong.api.dal.dao.inter.ITestUserTblDao.selectByPrimaryKey";
	private final static String UPDATE_BY_PRIMARY_KEY_STATEMENT = "com.edaisong.api.dal.dao.inter.ITestUserTblDao.updateByPrimaryKey";

	@Override
	public int deleteByPrimaryKey(Integer id) {
		Map<String, Object> paramMap = new HashedMap();
		paramMap.put("id", id);
		return SqlSessionUtil.wapperSession(superManSqlServerSessionFactory)
				.delete(DELETE_STATEMENT, paramMap);
	}

	@Override
	public int insert(String phoneNo) {

		Map<String, Object> paramMap = new HashedMap();
		paramMap.put("phoneNo", phoneNo);
		return SqlSessionUtil.wapperSession(superManSqlServerSessionFactory)
				.insert(INSERT_STATEMENT, paramMap);
	}

	@Override
	public int insertSelective(TestUserTbl record) {
		// TODO Auto-generated method stub
		// return 0;
		throw new UnsupportedOperationException();
	}

	@Override
	public TestUserTbl selectByPrimaryKey(Integer id) {
		Map<String, Object> paramMap = new HashedMap();
		paramMap.put("id", id);
		return SqlSessionUtil.wapperSession(
				superManReadOnlySqlServerSessionFactory).selectOne(
				SELECT_BY_PRIMARY_KEY_STATEMENT, paramMap);
	}

	@Override
	public int updateByPrimaryKeySelective(TestUserTbl record) {
		// TODO Auto-generated method stub
		// return 0;
		throw new UnsupportedOperationException();
	}

	@Override
	public int updateByPrimaryKey(TestUserTbl record) {
		Map<String, Object> paramMap = new HashedMap();
		paramMap.put("PhoneNo", record.getPhoneno());
		paramMap.put("id", record.getId());
		return SqlSessionUtil.wapperSession(superManSqlServerSessionFactory)
				.insert(UPDATE_BY_PRIMARY_KEY_STATEMENT, paramMap);
	}

	@Override
	public List<TestUserRecord> selectAllTestUsers() {
		List<TestUserRecord> list = SqlSessionUtil.wapperSession(
				superManReadOnlySqlServerSessionFactory).selectList(
				SELECT_ALL_TEST_USERS);
		return list;
	}

	@Override
	public int deleteByPhoneNo(String phoneNo) {
		Map<String, Object> paramMap = new HashedMap();
		paramMap.put("phoneNo", phoneNo);
		return SqlSessionUtil.wapperSession(superManSqlServerSessionFactory)
				.delete(DELETE_STATEMENT, paramMap);
	}

}
