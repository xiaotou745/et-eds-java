package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IAuthorityRoleMentMenuSetDao;
import com.edaisong.api.service.inter.IAuthorityRoleMentMenuSetService;
import com.edaisong.entity.AuthorityRoleMentMenuSet;
@Service
public class AuthorityRoleMentMenuSetService implements IAuthorityRoleMentMenuSetService{

	@Autowired
	private IAuthorityRoleMentMenuSetDao authorityRoleMentMenuSetDao;
	@Override
	public boolean modifyAuthList(List<AuthorityRoleMentMenuSet> authList) {
		return authorityRoleMentMenuSetDao.modifyAuthList(authList);
	}

}
