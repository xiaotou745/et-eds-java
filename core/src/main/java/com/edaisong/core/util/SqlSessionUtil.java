package com.edaisong.core.util;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class SqlSessionUtil {

	private SqlSession innerSession;

	private SqlSessionUtil(SqlSession session) {
		innerSession = session;
	}

	public static SqlSessionUtil wapperSession(SqlSessionFactory factory){
		SqlSession session = factory.openSession();
		return new SqlSessionUtil(session);
	}

	public <E> List<E> selectList(String statement, Object parameter) {
		try {
			return innerSession.selectList(statement, parameter);
		} catch (Exception e) {
			throw e;
		} finally {
			innerSession.close();
		}
	}

	public <E> List<E> selectList(String statement) {
		try {
			return innerSession.selectList(statement);
		} catch (Exception e) {
			throw e;
		} finally {
			innerSession.close();
		}
	}

	public <T> T selectOne(String statement, Object parameter) {
		try {
			return innerSession.selectOne(statement, parameter);
		} catch (Exception e) {
			throw e;
		} finally {
			innerSession.close();
		}
	}

	public <T> T selectOne(String statement) {
		try {
			return innerSession.selectOne(statement);
		} catch (Exception e) {
			throw e;
		} finally {
			innerSession.close();
		}
	}

	public int delete(String statement, Object parameter) {
		try {
			int result = innerSession.delete(statement, parameter);
			innerSession.commit();
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			innerSession.close();
		}
	}

	public int delete(String statement) {
		try {
			int result = innerSession.delete(statement);
			innerSession.commit();
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			innerSession.close();
		}
	}

	public int update(String statement, Object parameter) {
		try {
			int result = innerSession.update(statement, parameter);
			innerSession.commit();
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			innerSession.close();
		}
	}

	public int update(String statement) {
		try {
			int result = innerSession.update(statement);
			innerSession.commit();
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			innerSession.close();
		}
	}

	public int insert(String statement, Object parameter) {
		try {
			int result = innerSession.insert(statement, parameter);
			innerSession.commit();
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			innerSession.close();
		}
	}

	public int insert(String statement) {
		try {
			int result = innerSession.insert(statement);
			innerSession.commit();
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			innerSession.close();
		}
	}
	public <T> T getMapper(Class<T> type) {
		return innerSession.getMapper(type);
	}
}
