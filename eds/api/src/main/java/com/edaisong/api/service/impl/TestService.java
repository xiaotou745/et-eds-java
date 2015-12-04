package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.ITestDao;
import com.edaisong.api.service.inter.ITestService;

@Service
public class TestService implements ITestService {

	@Autowired
	private ITestDao testDao;

	@Override
	public void insert(String val) {
		testDao.insert(val);
	}
	
	
}
