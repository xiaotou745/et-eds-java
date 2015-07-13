package com.edaisong.api.service.inter;

import com.edaisong.entity.req.AuthorityMenuReq;
import com.edaisong.entity.resp.AuthorityMenuResp;

public interface IAuthorityMenuClassService {
	public AuthorityMenuResp getMenuListByUserID(AuthorityMenuReq req) ;
}
