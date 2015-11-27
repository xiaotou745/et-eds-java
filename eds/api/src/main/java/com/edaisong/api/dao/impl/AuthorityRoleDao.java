package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IAuthorityRoleDao;
import com.edaisong.entity.AuthorityRole;
@Repository
public class AuthorityRoleDao extends DaoBase implements IAuthorityRoleDao{

	@Override
	public int insert(AuthorityRole record) {
		return getMasterSqlSessionUtil().insert("IAuthorityRoleDao.insert", record);
	}

	@Override
	public int update(AuthorityRole record) {
	    return getMasterSqlSessionUtil().insert("IAuthorityRoleDao.update", record);
	}

	@Override
	public List<AuthorityRole> selectList() {
		 return getReadOnlySqlSessionUtil().selectList("IAuthorityRoleDao.selectList");
	}

}
