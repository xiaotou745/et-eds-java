package com.edaisong.api.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IClienterLocationDao;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.entity.ClienterLocation;
import com.edaisong.entity.common.Location;

@Repository
public class ClienterLocationDao extends DaoBase implements IClienterLocationDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ClienterLocation record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(ClienterLocation record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ClienterLocation selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(ClienterLocation record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ClienterLocation record) {
		// TODO Auto-generated method stub
		return 0;
	}

	  /**
     * 获得实时坐标
     * @author CaoHeYang
     * @param start
     * @param end
     * @param clienterId
     * @date 20150901
     * @return
     */
	@Override
	public List<Location> getLocationsByTime(Date start, Date end, int clienterId) {
		Map< String, Object> maps=new HashedMap();
		maps.put("start",  ParseHelper.ToDateString(start));
		maps.put("end",  ParseHelper.ToDateString(end));
		maps.put("clienterId",  clienterId);
		return getReadOnlySqlSessionUtil().selectList("com.edaisong.api.dao.inter.IClienterLocationDao.getLocationsByTime", maps);
	}

}
