package com.edaisong.api.dal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IGlobalConfigDao;
import com.edaisong.core.util.SqlSessionUtil;
import com.edaisong.entity.GlobalConfig;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.req.ConfigSaveReq;

@Repository
public class GlobalConfigDao implements IGlobalConfigDao {
	@Autowired
	private SqlSessionFactory superManReadOnlySqlServerSessionFactory;

	@Autowired
	private SqlSessionFactory superManSqlServerSessionFactory;

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
	 */
	@Override
	public List<GlobalConfigModel> getGlobalConfigByGroupId(Integer id) {

		String statement = "com.edaisong.api.dal.dao.inter.IGlobalConfigDao.getGlobalGroupConfig";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groupid", id);
		List<GlobalConfigModel> model = SqlSessionUtil.wapperSession(
				superManReadOnlySqlServerSessionFactory).selectList(statement,
				paramMap);
		return model;

	}

	/*
	 * 保存全局变量的值
	 */
	@Override
	public Boolean saveConfig(ConfigSaveReq par) {
		String statement = "com.edaisong.api.dal.dao.inter.IGlobalConfigDao.saveConfigValue";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", par.getId());
		paramMap.put("parvalue", par.getConfigValue());
		return SqlSessionUtil.wapperSession(superManSqlServerSessionFactory)
				.update(statement, paramMap) > 0;
	}

	/*
	 * 通过某个字段获取值
	 */
	@Override
	public String getConfigValueByKey(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
