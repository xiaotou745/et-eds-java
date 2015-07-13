package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.IAuthorityMenuClassDao;

import com.edaisong.api.service.inter.IAuthorityMenuClassService;

import com.edaisong.entity.MenuEntity;
import com.edaisong.entity.req.AuthorityMenuReq;
import com.edaisong.entity.resp.AuthorityMenuResp;

@Service
public class AuthorityMenuClassService implements IAuthorityMenuClassService {
	@Autowired
	private IAuthorityMenuClassDao dao;
	@Override
	public AuthorityMenuResp getMenuListByUserID(AuthorityMenuReq req) {
		AuthorityMenuResp resp = new AuthorityMenuResp();
		List<MenuEntity> listData = dao.getMenuListByUserID(
				req.getAccountId());
		resp.setMenuList(listData);
		return resp;
	}

}
