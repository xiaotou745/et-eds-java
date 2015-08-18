package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IAuthorityMenuClassDao;
import com.edaisong.api.service.inter.IAuthorityMenuClassService;
import com.edaisong.entity.MenuEntity;
import com.edaisong.entity.req.AuthorityMenuReq;

@Service
public class AuthorityMenuClassService implements IAuthorityMenuClassService {
	@Autowired
	private IAuthorityMenuClassDao dao;
	
	@Override
	public List<MenuEntity> getMenuListByUserID(AuthorityMenuReq req) {
		return dao.getMenuListByUserID(
				req.getAccountId());
	}

}
