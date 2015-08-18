package com.edaisong.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.IGlobalConfigDao;
import com.edaisong.api.service.inter.IGlobalConfigService;
import com.edaisong.entity.GlobalConfig;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.req.ConfigSaveReq;
/*
 * 管理员工具 
 * */
@Service
public class GlobalConfigService implements IGlobalConfigService {
	@Autowired
	private IGlobalConfigDao iGlobalConfigDao ;
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
		return iGlobalConfigDao.update(par);
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

}