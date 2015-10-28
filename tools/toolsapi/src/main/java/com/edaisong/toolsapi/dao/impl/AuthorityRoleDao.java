package com.edaisong.toolsapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.toolsapi.common.DaoBase;
import com.edaisong.toolsapi.dao.inter.IAuthorityRoleDao;
import com.edaisong.toolsentity.AuthorityRole;
@Repository
public class AuthorityRoleDao extends DaoBase implements IAuthorityRoleDao{

	@Override
	public int insert(AuthorityRole record) {
		return getMasterSqlSessionUtil().insert("com.edaisong.toolsapi.dao.inter.IAuthorityRoleDao.insert", record);
	}

	@Override
	public int update(AuthorityRole record) {
	    return getMasterSqlSessionUtil().insert("com.edaisong.toolsapi.dao.inter.IAuthorityRoleDao.update", record);
	}

	@Override
	public List<AuthorityRole> selectList() {
		 return getReadOnlySqlSessionUtil().selectList("com.edaisong.toolsapi.dao.inter.IAuthorityRoleDao.selectList");
	}

}
