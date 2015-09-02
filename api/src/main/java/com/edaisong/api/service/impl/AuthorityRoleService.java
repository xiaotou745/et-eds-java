package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IAuthorityRoleDao;
import com.edaisong.api.service.inter.IAuthorityRoleService;
import com.edaisong.entity.AuthorityRole;
@Service
public class AuthorityRoleService implements IAuthorityRoleService {
	@Autowired
	private IAuthorityRoleDao authorityRoleDao;

	@Override
	public int insert(AuthorityRole record) {
		return authorityRoleDao.insert(record);
	}

	@Override
	public int update(AuthorityRole record) {
		return authorityRoleDao.update(record);
	}

	@Override
	public List<AuthorityRole> selectList() {
		return authorityRoleDao.selectList();
	}

}
