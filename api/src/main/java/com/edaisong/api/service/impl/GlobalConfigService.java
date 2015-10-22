package com.edaisong.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IGlobalConfigDao;
import com.edaisong.api.redis.NetRedisService;
import com.edaisong.api.service.inter.IGlobalConfigService;
import com.edaisong.core.consts.RedissCacheKey;
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
	private NetRedisService redisService;
	/*
	 * 修改全局变量参数
	 * */
	@Override
	public int update(ConfigSaveReq par) {
		GlobalConfigModel model = iGlobalConfigDao.getGlobalConfigByPrimaryId(par.getId());
		int ret = iGlobalConfigDao.update(par);
		if(ret>0)
		{
			String key=String.format(RedissCacheKey.GlobalConfig_Key, par.getKeyName());
			redisService.remove(key);
			redisService.set(key, par.getConfigValue());
		}			
		
		return ret;
	}
	@Override
	public String getConfigValueByKey(int groupID,String key) {
		List<GlobalConfigModel> listDataConfigModels=iGlobalConfigDao.getGlobalConfigByGroupId(groupID);
		Map<String, String> resultMap=new HashMap<>();
		for (GlobalConfigModel globalConfigModel : listDataConfigModels) {
			resultMap.put(globalConfigModel.getKeyName().toUpperCase(), globalConfigModel.getValue());
		}
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
		int ret= iGlobalConfigDao.insert(par);
		if(ret>0)
		{
			String key=String.format(RedissCacheKey.GlobalConfig_Key, par.getKeyname());
			redisService.remove(key);
			redisService.set(key, par.getValue());
		}
		return ret;
	}
	
	@Override
	public PagedResponse<GlobalConfigModel> getPagedGlobalConfigModels(PagedGlobalConfigReq search) {
		return iGlobalConfigDao.getPagedGlobalConfigModels(search);
	}

}