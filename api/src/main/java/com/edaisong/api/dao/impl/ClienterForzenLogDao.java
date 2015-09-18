package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IClienterForzenLogDao;
import com.edaisong.entity.ClienterForzenLog;

@Repository
public class ClienterForzenLogDao extends DaoBase implements IClienterForzenLogDao {
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ClienterForzenLog record) { 
		return getMasterSqlSessionUtil().insert("com.edaisong.api.dao.inter.IClienterForzenLogDao.insert", record);
	}

	@Override
	public int insertSelective(ClienterForzenLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ClienterForzenLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(ClienterForzenLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ClienterForzenLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ClienterForzenLog> getList(int id) {
		List<ClienterForzenLog> forzenLogList = getReadOnlySqlSessionUtil().selectList( 
				"com.edaisong.api.dao.inter.IClienterForzenLogDao.getList", id);
		return forzenLogList;
	}

}
