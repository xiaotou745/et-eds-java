package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IClienterFinanceAccountDao;
import com.edaisong.entity.ClienterFinanceAccount;

/**
 *
 * @author  pengyi 
 * @date 2015年9月10日 下午5:51:42
 * @version 1.0
 * @parameter
 * @since
 */
@Repository
public class ClienterFinanceAccountDao extends DaoBase implements IClienterFinanceAccountDao{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean insert(ClienterFinanceAccount record) {
		return getMasterSqlSessionUtil().insert("IClienterFinanceAccountDao.insert",record) > 0;
	}

	@Override
	public int insertSelective(ClienterFinanceAccount record) {
		return getMasterSqlSessionUtil().insert("IClienterFinanceAccountDao.insertSelective",record);
	}

	@Override
	public ClienterFinanceAccount selectByPrimaryKey(Integer id) {
		return getReadOnlySqlSessionUtil().selectOne("IClienterFinanceAccountDao.selectByPrimaryKey",id);
	}

	@Override
	public boolean updateByPrimaryKeySelective(ClienterFinanceAccount record) {
		return getMasterSqlSessionUtil().insert("IClienterFinanceAccountDao.updateByPrimaryKeySelective",record) > 0;
	}

	@Override
	public int updateByPrimaryKey(ClienterFinanceAccount record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCountByClientId(int clienterId, int accountType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clienterId", clienterId);
		map.put("accountType", accountType);
		return getReadOnlySqlSessionUtil().selectOne("IClienterFinanceAccountDao.getCountByClientId", map);
	}

}
