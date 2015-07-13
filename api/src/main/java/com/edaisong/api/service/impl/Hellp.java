package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.business.SqlSessionFactoryPool;
import com.edaisong.api.service.inter.IAuthorityMenuClassService;
import com.edaisong.api.service.inter.ITestService;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.req.AuthorityMenuReq;
import com.edaisong.entity.req.TestServiceReq;
import com.edaisong.entity.resp.TestServiceResp;

public class Hellp {

	public static void main(String[] args) {
		ITestService testService = SqlSessionFactoryPool
				.getCustomBeanByType(ITestService.class);
		
		IAuthorityMenuClassService menuService = SqlSessionFactoryPool
				.getCustomBeanByType(IAuthorityMenuClassService.class);
		AuthorityMenuReq req2=new AuthorityMenuReq();
		req2.setAccountId("14");
		menuService.getMenuListByUserID(req2);

		TestServiceReq req = new TestServiceReq();
		req.setRecordType(9);
		req.setOperateTime("2015-01-01");
		TestServiceResp resp = testService.selectBusinessBalanceByID(req);

		List<BusinessBalanceRecord> listData = resp.getResultList();
		for (int i = 0; i < listData.size(); i++) {
			System.out.println(listData.get(i).getId() + "--"
					+ listData.get(i).getBusinessId() + "--"
					+ listData.get(i).getOperator() + "--"
					+ listData.get(i).getRecordType() + "--"
					+ listData.get(i).getRelationNo() + "--"
					+ listData.get(i).getRemark() + "--"
					+ listData.get(i).getStatus() + "--"
					+ listData.get(i).getWithwardId() + "--"
					+ listData.get(i).getAmount() + "--"
					+ listData.get(i).getBalance() + "--"
					+ listData.get(i).getOperateTime());
		}

		System.exit(0);
	}
}
