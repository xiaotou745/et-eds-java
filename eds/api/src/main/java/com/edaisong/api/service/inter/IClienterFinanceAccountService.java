package com.edaisong.api.service.inter;

import com.edaisong.entity.ClienterFinanceAccount;

/**
 *
 * @author  pengyi 
 * @date 2015年9月10日 下午5:59:53
 * @version 1.0
 * @parameter
 * @since
 */
public interface IClienterFinanceAccountService {
	int getCountByClientId(int clienterId, int accountType);
	
	boolean insert(ClienterFinanceAccount record);
	
	int insertSelective(ClienterFinanceAccount record);
	
	boolean updateByPrimaryKeySelective(ClienterFinanceAccount record);
	
	ClienterFinanceAccount selectByPrimaryKey(Integer id);
}
