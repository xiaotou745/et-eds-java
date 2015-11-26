package com.edaisong.toolsapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.toolsapi.common.DaoBase;
import com.edaisong.toolsapi.dao.inter.IAuthorityRoleMentMenuSetDao;
import com.edaisong.toolsentity.AuthorityRoleMentMenuSet;
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
