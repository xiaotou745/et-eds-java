package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IAuthorityAccountMenuSetDao;
import com.edaisong.api.service.inter.IAuthorityAccountMenuSetService;

@Service
public class AuthorityAccountMenuSetService implements IAuthorityAccountMenuSetService{

	@Autowired
	private IAuthorityAccountMenuSetDao authorityAccountMenuSetDao;
	@Override
	public List<Integer> getMenuIdsByAccountId(Integer id) {
		return authorityAccountMenuSetDao.getMenuIdsByAccountId(id);
	}

}
