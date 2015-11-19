package com.edaisong.toolsapi.common;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolsentity.domain.ConnectionInfo;

public class ToolsDaoBase {
	private SqlSessionFactory createFactory(String con) {
		try {
			ConnectionInfo conInfo = JsonUtil.str2obj(con, ConnectionInfo.class);
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			dataSource.setUrl(conInfo.getUrl());
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

	public SqlSessionUtil getReadOnlySqlSessionUtil(String con) {
		return SqlSessionUtil.wapperSession(createFactory(con));
	}

	public SqlSessionUtil getMasterSqlSessionUtil(String con) {
		return SqlSessionUtil.wapperSession(createFactory(con));
	}
}
