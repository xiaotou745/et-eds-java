package com.edaisong.toolsapi.common;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


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
