package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.req.AuthorityMenuReq;
import com.edaisong.entity.resp.AuthorityMenuResp;

public interface IAdminToolsService {

	public List<GlobalConfigModel> getGlobalConfigByGroupId(Integer id) ;
}
