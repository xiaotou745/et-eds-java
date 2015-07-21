package com.edaisong.api.service.inter;

import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.req.AuthorityMenuReq;
import com.edaisong.entity.resp.AuthorityMenuResp;

public interface IAdminToolsService {

	public GlobalConfigModel getGlobalConfigByGroupId(Integer id) ;
}
