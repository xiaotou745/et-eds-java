package com.edaisong.toolsapi.dao.inter;

import java.util.List;

import com.edaisong.toolsentity.GlobalConfig;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.domain.GlobalConfigModel;
import com.edaisong.toolsentity.req.ConfigSaveReq;
import com.edaisong.toolsentity.req.PagedGlobalConfigReq;

public interface IGlobalConfigDao {
	List<GlobalConfigModel> getGlobalConfigByGroupId(Integer id);
	GlobalConfigModel getGlobalConfigByPrimaryId(Integer id);
	int update(ConfigSaveReq par);
	int insert(GlobalConfig par);	
	PagedResponse<GlobalConfigModel> getPagedGlobalConfigModels(PagedGlobalConfigReq search);
//	GlobalGroupConfigModel GlobalConfigMethod(int groupId);
	
}