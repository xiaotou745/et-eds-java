package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.MenuEntity;
import com.edaisong.entity.req.AuthorityMenuReq;

public interface IAuthorityMenuClassService {
	public List<MenuEntity> getMenuListByUserID(AuthorityMenuReq req) ;
}
