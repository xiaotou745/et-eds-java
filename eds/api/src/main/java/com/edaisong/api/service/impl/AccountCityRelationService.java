package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IAccountCityRelationDao;
import com.edaisong.api.service.inter.IAccountCityRelationService;
import com.edaisong.entity.AccountCityRelation;
import com.edaisong.entity.AccountDeliveryRelation;

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
	/**
	 * 批量执行insert
	 */
	@Override
	public int modifyAuthList(List<AccountCityRelation> recordList) {
		int batch = 50;
		int result = 0;
		int num = recordList.size() / batch;
		int fix = recordList.size() % batch;
		for (int i = 0; i < num; i++) {
			List<AccountCityRelation> sublist = recordList.subList(batch* i, batch * (i + 1));
			result += accountCityRelationDao.modifyAuthList(sublist);
		}
		if (fix > 0) {
			List<AccountCityRelation> sublist = recordList.subList(recordList.size() - fix, recordList.size());
			result += accountCityRelationDao.modifyAuthList(sublist);
		}
		return result;
	}
}
