package com.edaisong.api.common;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.edaisong.core.util.SpringBeanHelper;

public class JDBCPoolAppender extends org.apache.log4j.jdbc.JDBCAppender {

	private SqlSessionFactory logSqlServerSessionFactory;

	public JDBCPoolAppender() {
		super();
		logSqlServerSessionFactory = (SqlSessionFactory) SpringBeanHelper
				.getCustomBean("superManLogSqlServerSessionFactory");
	}

	@Override
	protected void execute(String sql) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			if (stmt != null) {
				stmt.close();
			}
			e.printStackTrace();
		}
		stmt.close();
		closeConnection(con);
		System.out.println("Execute: " + sql);
	}

	@Override
	protected void closeConnection(Connection con) {
		try {
			if (connection != null && !connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Connection getConnection() throws SQLException {
		try {
			SqlSession session = logSqlServerSessionFactory.openSession();
			return session.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
