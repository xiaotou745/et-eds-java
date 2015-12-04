package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.ITestDao;
import com.edaisong.api.service.inter.ITestDouService;

@Service
public class TestDouService  implements ITestDouService{

	@Autowired
	ITestDao testDao;
	@Override
	public void insert(String val) {
		// TODO Auto-generated method stub
		testDao.insert(val);
	}

}
