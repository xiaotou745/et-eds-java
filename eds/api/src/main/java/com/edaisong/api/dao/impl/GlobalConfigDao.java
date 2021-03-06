package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IGlobalConfigDao;
import com.edaisong.entity.BusinessMessage;
import com.edaisong.entity.GlobalConfig;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.req.ConfigSaveReq;
import com.edaisong.entity.req.PagedGlobalConfigReq;

@Repository
public class GlobalConfigDao extends DaoBase implements IGlobalConfigDao {
	/*
	 * 获取全局配置
	 */
	@Override
	public List<GlobalConfigModel> getGlobalConfigByGroupId(Integer id) {

		String statement = "com.edaisong.api.dao.inter.IGlobalConfigDao.getGlobalGroupConfig";
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
	public int update(ConfigSaveReq par) {
		String statement = "com.edaisong.api.dao.inter.IGlobalConfigDao.saveConfigValue";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", par.getId());
		paramMap.put("parvalue", par.getConfigValue());
		return getMasterSqlSessionUtil().update(statement, paramMap);
	}

	/*
	 * 添加新的全局配置
	 */
	@Override
	public int insert(GlobalConfig par) {
		String statement = "com.edaisong.api.dao.inter.IGlobalConfigDao.insert";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("keyname", par.getKeyname());
		paramMap.put("value", par.getValue());
		paramMap.put("remark", par.getRemark());
		
		return getMasterSqlSessionUtil().insert(statement, paramMap);
	}

	@Override
	public PagedResponse<GlobalConfigModel> getPagedGlobalConfigModels(PagedGlobalConfigReq search) {
		PagedResponse<GlobalConfigModel> result = new PagedResponse<GlobalConfigModel>();
		result = getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dao.inter.IGlobalConfigDao.getPagedGlobalConfigModels", search);
		return result;
	}

	@Override
	public GlobalConfigModel getGlobalConfigByPrimaryId(Integer id) {
		GlobalConfigModel model = getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IGlobalConfigDao.getGlobalConfigByPrimaryId", id);
		return model;
	}
	
//	/**
//	 * 获取系统默认配置
//	 * @author 胡灵波
//	 * @Date 2015年8月14日 16:08:58
//	 * @return
//	 */
//	@Override
//    public GlobalGroupConfigModel GlobalConfigMethod(int groupId)
//    {
//    	GlobalGroupConfigModel model=null;
//    	
//		Map<String, Object> paramMap = new HashMap<>();
//		paramMap.put("GroupId", groupId);
//		model = getMasterSqlSessionUtil().selectOne(
//				"com.edaisong.api.dao.inter.IGlobalConfigDao.query",
//				paramMap);
//		
//		return model;   	
//    }   
   
}
