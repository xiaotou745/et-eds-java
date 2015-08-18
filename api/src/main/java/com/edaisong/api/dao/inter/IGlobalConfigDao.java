package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.GlobalConfig;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.domain.GlobalGroupConfigModel;
import com.edaisong.entity.req.ConfigSaveReq;

public interface IGlobalConfigDao {
	List<GlobalConfigModel> getGlobalConfigByGroupId(Integer id);
	int update(ConfigSaveReq par);
	int insert(GlobalConfig par);	
	
//	GlobalGroupConfigModel GlobalConfigMethod(int groupId);
	
}