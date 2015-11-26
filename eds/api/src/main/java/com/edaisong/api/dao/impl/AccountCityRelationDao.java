package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IAccountCityRelationDao;
import com.edaisong.entity.AccountCityRelation;

@Repository
public class AccountCityRelationDao extends DaoBase implements IAccountCityRelationDao {

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(AccountCityRelation record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(AccountCityRelation record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AccountCityRelation selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(AccountCityRelation record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(AccountCityRelation record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 获取后台用户可查看的城市集合
	 * @author CaoHeYang
	 * @date 20151125
	 * @param userId
	 * @return
	 */
	@Override
	public List<String> getAuthorityCitys(int userId){
	 return  getReadOnlySqlSessionUtil().selectList("IAccountCityRelationDao.getAuthorityCitys", userId);
	} 
	

}
