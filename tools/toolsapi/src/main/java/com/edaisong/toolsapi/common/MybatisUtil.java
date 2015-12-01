package com.edaisong.toolsapi.common;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import com.edaisong.toolscore.util.SpringBeanHelper;
import com.edaisong.toolsentity.domain.ConnectionInfo;

/**
 * 根据配置创建连接工厂
 * @author hailongzhao
 * @date 20151130
 *
 */
public class MybatisUtil {
	/**
	 * 根据配置创建sqlserver连接工厂
	 * @param conInfo
	 * @date 20151130
	 * @return
	 */
	private static SqlSessionFactory createFactory(ConnectionInfo conInfo) {
		try {
			String sqlserver = "jdbc:sqlserver://%s:%s;DatabaseName=%s;";
			String url= String.format(sqlserver,conInfo.getHost(),conInfo.getPort(),conInfo.getDataBase());
			BasicDataSource dataSource=new BasicDataSource();
			dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			dataSource.setUrl(url);
			dataSource.setUsername(conInfo.getUserName());
			dataSource.setPassword(conInfo.getPassWord());
			DefaultSqlSessionFactory sessionFactory=(DefaultSqlSessionFactory)SpringBeanHelper.getCustomBean("customerSqlServerSessionFactory");
			Environment ev=sessionFactory.getConfiguration().getEnvironment();
			Environment newEnv=new Environment(ev.getId(),ev.getTransactionFactory(),dataSource);
			sessionFactory.getConfiguration().setEnvironment(newEnv);
			return sessionFactory;
		} catch (Exception e) {
			throw new RuntimeException("创建sqlserver连接工厂时出错:" + e.getMessage());
		}
	}

	/**
	 * 封装SqlSessionUtil执行sqlmapper中的sql
	 * @param conInfo
	 * @author hailongzhao
	 * @date 20151201
	 * @return
	 */
	public static SqlSessionUtil getSqlSessionUtil(ConnectionInfo conInfo) {
		SqlSessionFactory factory=createFactory(conInfo);
		return SqlSessionUtil.wapperSession(factory);
	}
	/**
	 * 在指定的db中执行动态查询sql
	 * @param conInfo
	 * @author hailongzhao
	 * @date 20151201
	 * @param sql
	 * @return
	 */
	public static List<Map<String, String>> dynamicSelectList(ConnectionInfo conInfo,String sql){
		List<Map<String, String>> rsult=getSqlSessionUtil(conInfo).selectList("IDynamicSqlDao.selectList",sql);
		return rsult;
	}
	/**
	 * 在指定的db中执行动态insert,delete,update语句
	 * @param conInfo
	 * @param sql
	 * @return
	 */
	public static int dynamicUpdateDb(ConnectionInfo conInfo,String sql){
		return getSqlSessionUtil(conInfo).update("IDynamicSqlDao.updateDb",sql);
	}
}
