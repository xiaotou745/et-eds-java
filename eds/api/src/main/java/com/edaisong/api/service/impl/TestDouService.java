package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.ITestDao;
import com.edaisong.api.service.inter.ITestDouService;
import com.edaisong.core.inter.IJobDo;

@Service
public class TestDouService  implements IJobDo{

	@Autowired
	ITestDao testDao;

	public void insert(String val) {
		// TODO Auto-generated method stub
		testDao.insert(val);
	}
	@Override
	public void execute() {
		testDao.insert("1000");
		
	}

}
