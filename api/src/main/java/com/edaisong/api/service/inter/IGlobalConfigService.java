package com.edaisong.api.service.inter;

import java.util.List;
import java.util.Map;

import com.edaisong.entity.GlobalConfig;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.req.AuthorityMenuReq;
import com.edaisong.entity.req.ConfigSaveReq;

public interface IGlobalConfigService {

	 List<GlobalConfigModel> getGlobalConfigByGroupId(Integer id) ;
	 int update(ConfigSaveReq par);
	 String getConfigValueByKey(int groupID,String key);
	 int insert(GlobalConfig par);
}
