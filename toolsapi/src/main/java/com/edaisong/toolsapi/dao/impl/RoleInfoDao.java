package com.edaisong.toolsapi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.toolsapi.common.DaoBase;
import com.edaisong.toolsapi.dao.inter.IRoleInfoDao;
import com.edaisong.toolsentity.RoleInfo; 
@Repository
public class RoleInfoDao extends DaoBase implements IRoleInfoDao{

	@Override
	public int insert(RoleInfo record) {
		return getMasterSqlSessionUtil().insert("com.renrentui.renrenapi.dao.inter.IRoleInfoDao.insert", record);
	}

	@Override
	public int update(RoleInfo record) {
	    return getMasterSqlSessionUtil().insert("com.renrentui.renrenapi.dao.inter.IRoleInfoDao.update", record);
	}

	@Override
	public List<RoleInfo> selectList() {
		 return getReadOnlySqlSessionUtil().selectList("com.renrentui.renrenapi.dao.inter.IRoleInfoDao.selectList");
	}

}
