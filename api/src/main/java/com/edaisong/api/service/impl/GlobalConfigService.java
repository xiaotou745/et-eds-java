package com.edaisong.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IGlobalConfigDao;
import com.edaisong.api.service.inter.IGlobalConfigService;
import com.edaisong.core.cache.redis.RedisService;
import com.edaisong.core.consts.RedissCacheKey;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.entity.GlobalConfig;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.req.ConfigSaveReq;
import com.edaisong.entity.req.PagedGlobalConfigReq;
/*
 * 管理员工具 
 * */
@Service
public class GlobalConfigService implements IGlobalConfigService {
	@Autowired
	private IGlobalConfigDao iGlobalConfigDao ;
	@Autowired
	private RedisService redisService;
	/*
	 * 获取全局配置变量
	 * 茹化肖
	 * 2015年7月20日17:48:31
	 * */
	@Override
	public List<GlobalConfigModel> getGlobalConfigByGroupId(Integer groupID) {
		return iGlobalConfigDao.getGlobalConfigByGroupId(groupID);
	}
	/*
	 * 修改全局变量参数
	 * */
	@Override
	public int update(ConfigSaveReq par) {
		GlobalConfigModel model = iGlobalConfigDao.getGlobalConfigByPrimaryId(par.getId());
		int ret = iGlobalConfigDao.update(par);
		if(model != null){
			redisService.remove(getConfigCacheKey(model.getGroupId()));
		}
		return ret;
	}
	@Override
	public String getConfigValueByKey(int groupID,String key) {
		Map<String, String> resultMap=getGlobalConfigMapByGroupId(groupID);
		if (resultMap.containsKey(key.toUpperCase())) {
			return resultMap.get(key.toUpperCase());
		}
		return "";
	}
	/*
	 * 添加新的全局配置
	 * */
	@Override
	public int insert(GlobalConfig par) {
		redisService.remove(getConfigCacheKey(par.getGroupid()));
		return iGlobalConfigDao.insert(par);
	}

	private Map<String, String> getGlobalConfigMapByGroupId(Integer id) {
		List<GlobalConfigModel> listDataConfigModels=iGlobalConfigDao.getGlobalConfigByGroupId(id);
		Map<String, String> resultMap=new HashMap<>();
		for (GlobalConfigModel globalConfigModel : listDataConfigModels) {
			resultMap.put(globalConfigModel.getKeyName().toUpperCase(), globalConfigModel.getValue());
		}
		return resultMap;
	}
	
	@Override
	public PagedResponse<GlobalConfigModel> getPagedGlobalConfigModels(PagedGlobalConfigReq search) {
		return iGlobalConfigDao.getPagedGlobalConfigModels(search);
	}

	/**
	 * 获得全局配置缓存key
	 * @param groupId
	 * @return
	 */
	private String getConfigCacheKey(int groupId){
		String globalVersion = PropertyUtils.getProperty("GlobalVersion");
		return String.format(RedissCacheKey.Ets_Dao_GlobalConfig_GlobalConfigGet+globalVersion, groupId);
	}
}