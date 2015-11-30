package com.edaisong.toolsapi.common;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisTemplate;

import com.edaisong.toolscore.enums.ServerType;
import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolsentity.domain.ConnectionInfo;

/**
 * 根据配置创建连接工厂
 * @author hailongzhao
 * @date 20151130
 *
 */
public class DaoUtil {
	private SqlSessionFactory factory;
	public DaoUtil(ConnectionInfo conInfo){
		factory=createFactory(conInfo);
	}
	/**
	 * 根据配置创建sqlserver连接工厂
	 * @param conInfo
	 * @date 20151130
	 * @return
	 */
	private SqlSessionFactory createFactory(ConnectionInfo conInfo) {
		try {
			String sqlserver = "jdbc:sqlserver://%s:%s;DatabaseName=%s;";
			String url= String.format(sqlserver,conInfo.getHost(),conInfo.getPort(),conInfo.getDataBase());
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			dataSource.setUrl(url);
			dataSource.setUsername(conInfo.getUserName());
			dataSource.setPassword(conInfo.getPassWord());
			SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
			Resource[] locations = { new ClassPathResource("classpath:conf/mybatis/*.xml") };
			factory.setMapperLocations(locations);
			factory.setDataSource(dataSource);
			return factory.getObject();
		} catch (Exception e) {
			throw new RuntimeException("创建sqlserver连接工厂时出错:" + e.getMessage());
		}
	}

	public SqlSessionUtil getReadOnlySqlSessionUtil() {
		return SqlSessionUtil.wapperSession(factory);
	}

	public SqlSessionUtil getMasterSqlSessionUtil() {
		return SqlSessionUtil.wapperSession(factory);
	}
}
