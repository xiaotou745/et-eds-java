package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IClienterFinanceAccountDao;
import com.edaisong.api.service.inter.IClienterFinanceAccountService;
import com.edaisong.entity.ClienterFinanceAccount;

/**
 *
 * @author  pengyi 
 * @date 2015年9月10日 下午6:00:10
 * @version 1.0
 * @parameter
 * @since
 */
@Service
public class ClienterFinanceAccountService implements IClienterFinanceAccountService{

	@Autowired
	private IClienterFinanceAccountDao clienterFinanceAccountDao;
	@Override
	public int getCountByClientId(int clienterId, int accountType) {
		return clienterFinanceAccountDao.getCountByClientId(clienterId,accountType);
	}
	@Override
	public boolean insert(ClienterFinanceAccount record) {
		return clienterFinanceAccountDao.insert(record);
	}
	@Override
	public int insertSelective(ClienterFinanceAccount record) {
		return clienterFinanceAccountDao.insertSelective(record);
	}
	@Override
	public boolean updateByPrimaryKeySelective(ClienterFinanceAccount record) {
		return clienterFinanceAccountDao.updateByPrimaryKeySelective(record);
	}
	@Override
	public ClienterFinanceAccount selectByPrimaryKey(Integer id) {
		return clienterFinanceAccountDao.selectByPrimaryKey(id);
	}

}
