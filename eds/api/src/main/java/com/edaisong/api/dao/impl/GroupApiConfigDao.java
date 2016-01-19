package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.Map;




import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IGroupApiConfigDao;
import com.edaisong.entity.GroupApiConfig;

@Repository
public class GroupApiConfigDao extends DaoBase implements IGroupApiConfigDao {

	@Override
	public int deleteByPrimaryKey(Integer appid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(GroupApiConfig record) {
		

		return getMasterSqlSessionUtil()
				.insert("IGroupApiConfigDao.insert",
						record);
	}

	@Override
	public int insertSelective(GroupApiConfig record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GroupApiConfig selectByPrimaryKey(Integer appid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(GroupApiConfig record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(GroupApiConfig record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
