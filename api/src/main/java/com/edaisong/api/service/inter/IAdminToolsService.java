package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.GlobalConfig;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.req.AuthorityMenuReq;
import com.edaisong.entity.req.ConfigSaveReq;

public interface IAdminToolsService {

	 List<GlobalConfigModel> getGlobalConfigByGroupId(Integer id) ;
	 Boolean saveConfig(ConfigSaveReq par);
	 String getConfigValueByKey(String key);
	 Boolean addConfig(GlobalConfig par);
}
