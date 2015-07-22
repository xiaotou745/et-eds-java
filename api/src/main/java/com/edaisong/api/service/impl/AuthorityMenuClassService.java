package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.IAuthorityMenuClassDao;
import com.edaisong.api.service.inter.IAuthorityMenuClassService;
import com.edaisong.entity.MenuEntity;
import com.edaisong.entity.common.ResponseCode;
import com.edaisong.entity.req.AuthorityMenuReq;
import com.edaisong.entity.resp.AuthorityMenuResp;

@Service
public class AuthorityMenuClassService implements IAuthorityMenuClassService {
	@Autowired
	private IAuthorityMenuClassDao dao;
	
	@Override
	public AuthorityMenuResp getMenuListByUserID(AuthorityMenuReq req) {
		AuthorityMenuResp resp = new AuthorityMenuResp();
		if(req==null||
			req.getAccountId()==null||
			req.getAccountId().isEmpty()){
			resp.setResponseCode(ResponseCode.PARAMETER_NULL_ERROR);
			resp.setMessage("入参或AccountId不能为空");
			return resp;
		}
		
		List<MenuEntity> listData = dao.getMenuListByUserID(
				req.getAccountId());
		resp.setMenuList(listData);
		return resp;
	}

}
