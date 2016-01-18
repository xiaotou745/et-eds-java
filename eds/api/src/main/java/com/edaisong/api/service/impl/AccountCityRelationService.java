package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IAccountCityRelationDao;
import com.edaisong.api.service.inter.IAccountCityRelationService;
import com.edaisong.entity.AccountCityRelation;

@Service
public class AccountCityRelationService implements IAccountCityRelationService {
	@Autowired
	private IAccountCityRelationDao accountCityRelationDao;
	/**
		 * 获取后台用户可查看的城市集合
		 * @author CaoHeYang
		 * @date 20151125
		 * @param userId
		 * @return
		 */
	@Override
	  public  List<Integer>  getAuthorityCitys(int userId){
			return accountCityRelationDao.getAuthorityCitys(userId);
	  }
	@Override
	public int modifyAuthList(List<AccountCityRelation> recordList) {
		return accountCityRelationDao.modifyAuthList(recordList);
	}
}
