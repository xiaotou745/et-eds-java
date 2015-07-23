package com.edaisong.api.dal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IGlobalConfigDao;
import com.edaisong.entity.GlobalConfig;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.req.ConfigSaveReq;

@Repository
public class GlobalConfigDao extends DaoBase implements IGlobalConfigDao {

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
		List<GlobalConfigModel> model = getReadOnlySqlSessionUtil().selectList(
				statement, paramMap);
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
		return getMasterSqlSessionUtil().update(statement, paramMap) > 0;
	}

	/*
	 * 通过某个字段获取值
	 */
	@Override
	public String getConfigValueByKey(String key) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * 添加新的全局配置
	 * */
	@Override
	public Boolean addConfig(GlobalConfig par) {
		// TODO Auto-generated method stub
		String statement = "com.edaisong.api.dal.dao.inter.IGlobalConfigDao.insertConifg";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("keyname", par.getKeyname());
		paramMap.put("value", par.getValue());
		paramMap.put("remark", par.getRemark());
		return SqlSessionUtil.wapperSession(superManSqlServerSessionFactory)
				.insert(statement, paramMap) > 0;
	}

}
