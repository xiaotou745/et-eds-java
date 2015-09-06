package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IBIService;
import com.edaisong.entity.Everyday;

@Service
public class BIService implements IBIService {

	@Autowired
	private IBIService biDao;

	@Override
	public List<Everyday> queryEveryDay() {
		List<Everyday> list = biDao.queryEveryDay();
		return list;
	}

}
