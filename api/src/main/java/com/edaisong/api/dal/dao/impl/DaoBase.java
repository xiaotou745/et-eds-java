package com.edaisong.api.dal.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edaisong.core.util.SqlSessionUtil;
@Repository
public class DaoBase {
	@Autowired
	private SqlSessionFactory superManReadOnlySqlServerSessionFactory;

	@Autowired
	private SqlSessionFactory superManSqlServerSessionFactory;
	
	public SqlSessionUtil getReadOnlySqlSessionUtil() {
		return SqlSessionUtil.wapperSession(superManReadOnlySqlServerSessionFactory);
	}
	public SqlSessionUtil getMasterSqlSessionUtil() {
		return SqlSessionUtil.wapperSession(superManSqlServerSessionFactory);
	}
}
