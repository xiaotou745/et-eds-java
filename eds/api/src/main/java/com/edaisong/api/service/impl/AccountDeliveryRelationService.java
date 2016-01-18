package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IAccountDeliveryRelationDao;
import com.edaisong.api.service.inter.IAccountDeliveryRelationService;
import com.edaisong.entity.AccountDeliveryRelation;

@Service
public class AccountDeliveryRelationService implements IAccountDeliveryRelationService{

	@Autowired
	private IAccountDeliveryRelationDao accountDeliveryRelationDao;
	/**
	 * 批量执行insert
	 */
	@Override
	public int modifyAuthList(List<AccountDeliveryRelation> recordList) {
		int batch = 50;
		int result = 0;
		int num = recordList.size() / batch;
		int fix = recordList.size() % batch;
		for (int i = 0; i < num; i++) {
			List<AccountDeliveryRelation> sublist = recordList.subList(batch* i, batch * (i + 1));
			result += accountDeliveryRelationDao.modifyAuthList(sublist);
		}
		if (fix > 0) {
			List<AccountDeliveryRelation> sublist = recordList.subList(recordList.size() - fix, recordList.size());
			result += accountDeliveryRelationDao.modifyAuthList(sublist);
		}
		return result;
	}

	@Override
	public List<Integer> getAuthorityCitys(int userId) {
return accountDeliveryRelationDao.getAuthorityCitys(userId);
	}

}
