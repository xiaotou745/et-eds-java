package com.edaisong.api.service.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IGlobalConfigDao;
import com.edaisong.api.redis.NetRedisService;
import com.edaisong.api.redis.RedisService;
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
		/*if(model != null){
			redisService.remove(RedissCacheKey.GlobalConfig_Key+model.getGroupId());
		}*/
		
		if(ret>0)
		{
			switch(par.getKeyName())
			{	
		    
				case "PushRadius":
				{
					String key=MessageFormat.format(RedissCacheKey.GlobalConfig_PushRadius, 0); 
					redisService.set(key, par.getConfigValue());
					break;
				}
				case "AllFinishedOrderUploadTimeInterval":
				{
					String key=MessageFormat.format(RedissCacheKey.GlobalConfig_AllFinishedOrderUploadTimeInterval, 0); 
					redisService.set(key, par.getConfigValue());
					break;
				}
				case "SearchClienterLocationTimeInterval":
				{
					String key=MessageFormat.format(RedissCacheKey.GlobalConfig_SearchClienterLocationTimeInterval, 0); 
					redisService.set(key, par.getConfigValue());
					break;
				}
				case "HasUnFinishedOrderUploadTimeInterval":
				{
					String key=MessageFormat.format(RedissCacheKey.GlobalConfig_HasUnFinishedOrderUploadTimeInterval, 0); 
					redisService.set(key, par.getConfigValue());
					break;
				}
				case "BusinessUploadTimeInterval":
				{
					String key=MessageFormat.format(RedissCacheKey.GlobalConfig_BusinessUploadTimeInterval, 0); 
					redisService.set(key, par.getConfigValue());
					break;
				}
				case "ClienterWithdrawCommissionAccordingMoney":
				{
					String key=MessageFormat.format(RedissCacheKey.GlobalConfig_ClienterWithdrawCommissionAccordingMoney, 0); 
					redisService.set(key, par.getConfigValue());
					break;
				}
				case "ExclusiveOrderTime":
				{
					String key=MessageFormat.format(RedissCacheKey.GlobalConfig_ExclusiveOrderTime, 0); 
					redisService.set(key, par.getConfigValue());
					break;
				}
				case "ClienterOrderPageSize":
				{
					String key=MessageFormat.format(RedissCacheKey.GlobalConfig_ClienterOrderPageSize, 0); 
					redisService.set(key, par.getConfigValue());
					break;
				}
				case "CompleteTimeSet":
				{
					String key=MessageFormat.format(RedissCacheKey.GlobalConfig_CompleteTimeSet, 0); 
					redisService.set(key, par.getConfigValue());
					break;
				}
				case "EmployerTaskTimeSet":
				{
					String key=MessageFormat.format(RedissCacheKey.GlobalConfig_EmployerTaskTimeSet, 0); 
					redisService.set(key, par.getConfigValue());
					break;
				}			
	
				case "WithdrawCommission":
				{
					String key=MessageFormat.format(RedissCacheKey.GlobalConfig_WithdrawCommission, 0); 
					redisService.set(key, par.getConfigValue());
					break;
				}
				case "OrderCountSetting":
				{
					String key=MessageFormat.format(RedissCacheKey.GlobalConfig_OrderCountSetting, 0); 
					redisService.set(key, par.getConfigValue());
					break;
				}
				case "YeepayWithdrawCommission":
				{
					String key=MessageFormat.format(RedissCacheKey.GlobalConfig_YeepayWithdrawCommission, 0); 
					redisService.set(key, par.getConfigValue());
					break;
				}
				case "GrabToCompleteDistance":
				{
					String key=MessageFormat.format(RedissCacheKey.GlobalConfig_GrabToCompleteDistance, 0); 
					redisService.set(key, par.getConfigValue());
					break;
				}
				case "AlipayWithdrawCommission":
				{
					String key=MessageFormat.format(RedissCacheKey.GlobalConfig_AlipayWithdrawCommission, 0); 
					redisService.set(key, par.getConfigValue());
					break;
				}
				case "AlipayPassword":
				{
					String key=MessageFormat.format(RedissCacheKey.GlobalConfig_AlipayPassword, 0); 
					redisService.set(key, par.getConfigValue());
					break;
				}					
			}		
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
		//redisService.remove(RedissCacheKey.GlobalConfig_Key+par.getGroupid());
		return iGlobalConfigDao.insert(par);
	}
	
	@Override
	public PagedResponse<GlobalConfigModel> getPagedGlobalConfigModels(PagedGlobalConfigReq search) {
		return iGlobalConfigDao.getPagedGlobalConfigModels(search);
	}

}