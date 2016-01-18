package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IAccountDeliveryRelationDao;
import com.edaisong.entity.AccountDeliveryRelation;
@Repository
public class AccountDeliveryRelationDao extends DaoBase implements IAccountDeliveryRelationDao{

	@Override
	public List<Integer> getAuthorityCitys(int userId){
	 return  getReadOnlySqlSessionUtil().selectList("IAccountDeliveryRelationDao.getAuthorityCitys", userId);
	}

	@Override
	public int modifyAuthList(List<AccountDeliveryRelation> recordList) {
		 return  getMasterSqlSessionUtil().update("IAccountDeliveryRelationDao.modifyAuthList", recordList);
	} 

}
