package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IGroupBusinessLogDao;
import com.edaisong.entity.GroupBusinessLog;
@Repository
public class GroupBusinessLogDao extends DaoBase implements IGroupBusinessLogDao {

	@Override
	public List<GroupBusinessLog> getList(int id) {  
		List<GroupBusinessLog> result = getReadOnlySqlSessionUtil().selectList( 
				"com.edaisong.api.dao.inter.IGroupBusinessLogDao.getList", id);
		return result;
	 
	}

	@Override
	public int insert(GroupBusinessLog gbl) {
		 
		return getMasterSqlSessionUtil().insert("com.edaisong.api.dao.inter.IGroupBusinessLogDao.insert",gbl);
	} 
}