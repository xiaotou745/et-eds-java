package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IAccountCityRelationDao;
import com.edaisong.entity.AccountCityRelation;

@Repository
public class AccountCityRelationDao extends DaoBase implements IAccountCityRelationDao {

	
	/**
	 * 获取后台用户可查看的城市集合
	 * @author CaoHeYang
	 * @date 20151125
	 * @param userId
	 * @return
	 */
	@Override
	public List<Integer> getAuthorityCitys(int userId){
	 return  getReadOnlySqlSessionUtil().selectList("IAccountCityRelationDao.getAuthorityCitys", userId);
	}

	@Override
	public int modifyAuthList(List<AccountCityRelation> recordList) {
		 return  getMasterSqlSessionUtil().update("IAccountCityRelationDao.modifyAuthList", recordList);
	} 
	

}
