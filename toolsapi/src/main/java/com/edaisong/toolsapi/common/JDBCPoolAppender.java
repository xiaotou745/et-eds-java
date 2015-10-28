package com.edaisong.toolsapi.common;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.edaisong.toolscore.util.SpringBeanHelper;

public class JDBCPoolAppender extends org.apache.log4j.jdbc.JDBCAppender {

	private SqlSessionFactory logSqlServerSessionFactory;

	public JDBCPoolAppender() {
		super();
	}

	@Override
	protected void execute(String sql) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		SqlSession session = null;
		try {
			if (logSqlServerSessionFactory==null) {
				logSqlServerSessionFactory = (SqlSessionFactory) SpringBeanHelper
						.getCustomBean("superManLogSqlServerSessionFactory");
			}
			session = logSqlServerSessionFactory.openSession();
			con = session.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}

			if (session != null) {
				session.close();
			}
		}
		// System.out.println("Execute: " + sql);
	}
}
