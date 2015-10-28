package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.GlobalConfig;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.req.ConfigSaveReq;
import com.edaisong.entity.req.PagedGlobalConfigReq;

public interface IGlobalConfigService {
	 int update(ConfigSaveReq par);
	 String getConfigValueByKey(int groupID,String key);
	 int insert(GlobalConfig par);
	 PagedResponse<GlobalConfigModel> getPagedGlobalConfigModels(PagedGlobalConfigReq search);
}
