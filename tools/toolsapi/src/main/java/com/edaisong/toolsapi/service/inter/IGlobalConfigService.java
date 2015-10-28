package com.edaisong.toolsapi.service.inter;
 
import com.edaisong.toolsentity.GlobalConfig;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.domain.GlobalConfigModel;
import com.edaisong.toolsentity.req.ConfigSaveReq;
import com.edaisong.toolsentity.req.PagedGlobalConfigReq;

public interface IGlobalConfigService {
	 int update(ConfigSaveReq par);
	 String getConfigValueByKey(int groupID,String key);
	 int insert(GlobalConfig par);
	 PagedResponse<GlobalConfigModel> getPagedGlobalConfigModels(PagedGlobalConfigReq search);
}
