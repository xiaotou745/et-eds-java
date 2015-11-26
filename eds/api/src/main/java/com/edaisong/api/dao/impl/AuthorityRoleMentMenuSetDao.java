package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IAuthorityRoleMentMenuSetDao;
import com.edaisong.entity.AuthorityRoleMentMenuSet;
@Repository
public class AuthorityRoleMentMenuSetDao extends DaoBase implements
		IAuthorityRoleMentMenuSetDao {

	@Override
	public boolean modifyAuthList(List<AuthorityRoleMentMenuSet> authList) {
		return getMasterSqlSessionUtil()
				.update("IAuthorityRoleMentMenuSetDao.modifyAuthList",
						authList) > 0;
	}

}
