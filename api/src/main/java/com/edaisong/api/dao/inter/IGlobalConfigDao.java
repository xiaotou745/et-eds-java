package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.GlobalConfig;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.req.ConfigSaveReq;
import com.edaisong.entity.req.PagedGlobalConfigReq;

public interface IGlobalConfigDao {
	List<GlobalConfigModel> getGlobalConfigByGroupId(Integer id);
	int update(ConfigSaveReq par);
	int insert(GlobalConfig par);	
	PagedResponse<GlobalConfigModel> getPagedGlobalConfigModels(PagedGlobalConfigReq search);
//	GlobalGroupConfigModel GlobalConfigMethod(int groupId);
	
}