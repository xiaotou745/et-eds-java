package com.edaisong.api.dal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IGlobalConfigDao;
import com.edaisong.entity.GlobalConfig;
import com.edaisong.entity.MenuEntity;
import com.edaisong.entity.domain.GlobalConfigModel;
@Repository
public class GlobalConfigDao implements IGlobalConfigDao{
	@Autowired
	private SqlSessionFactory superManReadOnlySqlServerSessionFactory;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(GlobalConfig record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(GlobalConfig record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GlobalConfig selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(GlobalConfig record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(GlobalConfig record) {
		// TODO Auto-generated method stub
		return 0;
	}
	/*
	 * 获取全局配置
	 * */
	@Override
	public List<GlobalConfigModel> getGlobalConfigByGroupId(Integer id) {
		// TODO Auto-generated method stub
		SqlSession session = superManReadOnlySqlServerSessionFactory
				.openSession();
		try {
			String statement ="com.edaisong.api.dal.dao.inter.IGlobalConfigDao.getGlobalGroupConfig";
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("groupid", id);
			List<GlobalConfigModel> model = session.selectList(statement, paramMap);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

}
