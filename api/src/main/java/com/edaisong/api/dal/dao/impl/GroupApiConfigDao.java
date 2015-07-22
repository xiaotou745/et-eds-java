package com.edaisong.api.dal.dao.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IGroupApiConfigDao;
import com.edaisong.entity.GroupApiConfig;

@Repository
public class GroupApiConfigDao implements IGroupApiConfigDao {

	@Autowired
	private SqlSessionFactory superManReadOnlySqlServerSessionFactory;

	@Override
	public int deleteByPrimaryKey(Integer appid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(GroupApiConfig record) {
		// TODO Auto-generated method stub
		SqlSession session = superManReadOnlySqlServerSessionFactory
				.openSession();
		try {			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("appkey", record.getAppkey());			
			paramMap.put("appsecret", record.getAppsecret());			
			paramMap.put("appversion", record.getAppversion());
			paramMap.put("groupid", record.getGroupid());	
	
			int result = session
					.insert("com.edaisong.api.dal.dao.inter.IGroupApiConfigDao.insert",
							paramMap);
			session.commit(); 
			System.out.println("GroupApiConfigDao-insert影响行数" + result);
		} catch (Exception e) {
			System.out.println("GroupApiConfigDao-insert-Exception异常" + e.getMessage());
		} finally {
			session.close();
		}
		return 0;
	}

	@Override
	public int insertSelective(GroupApiConfig record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GroupApiConfig selectByPrimaryKey(Integer appid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(GroupApiConfig record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(GroupApiConfig record) {
		// TODO Auto-generated method stub
		return 0;
	}	

}
