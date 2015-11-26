package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IClienterBindOptionLogDao;
import com.edaisong.entity.ClienterBindOptionLog;
@Repository
public class ClienterBindOptionLogDao extends DaoBase implements IClienterBindOptionLogDao{

	@Override
	public int insert(ClienterBindOptionLog record) {
return getMasterSqlSessionUtil().insert("IClienterBindOptionLogDao.selectList", record);
	}

	@Override
	public List<ClienterBindOptionLog> selectList(Long businessId,
			long clienterId) {
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("businessId", businessId);
		param.put("clienterId", clienterId);
		return getReadOnlySqlSessionUtil().selectList("IClienterBindOptionLogDao.selectList", param);
	}

}
