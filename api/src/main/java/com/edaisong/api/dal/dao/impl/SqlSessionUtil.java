package com.edaisong.api.dal.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.edaisong.entity.common.PagedRequestBase;
import com.edaisong.entity.common.RequestBase;
import com.edaisong.entity.common.PagedResponse;

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

	/**
	 * 分页查询  
	 * @param statement
	 * @param parameter
	 * @author CaoHeYang
	 * @Date 20150729  
	 * @return
	 */
	public <E> PagedResponse<E> selectPageList(String statement, Object parameter) {
		try {
			PagedRequestBase basemodel=  (PagedRequestBase)parameter;
			if (basemodel.getCurrentPage()==0) {
				basemodel.setCurrentPage(1);  //默认第一页
			}
			if(basemodel.getPageSize()==0){
				basemodel.setPageSize(15);  //默认页容量
			}
			PagedResponse<E> result=new PagedResponse<E>();
			result.setResultList(innerSession.selectList(statement, parameter));
			result.setCurrentPage(basemodel.getCurrentPage());
			result.setPageSize(basemodel.getPageSize());
			result.setTotalPage(basemodel.getTotalPage());
			result.setTotalRecord(basemodel.getTotalRecord());
			return result;
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
