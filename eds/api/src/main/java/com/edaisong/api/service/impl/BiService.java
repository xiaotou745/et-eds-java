package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IBiDao;
import com.edaisong.api.service.inter.IBiService;
import com.edaisong.entity.Everyday;
import com.edaisong.entity.domain.LineHistoryModel;

@Service
public class BiService implements IBiService {

	@Autowired
	private IBiDao biDao;

	@Override
	public List<Everyday> queryEveryDay() {
		List<Everyday> list = biDao.queryEveryDayDao();
		return list;
	}

	@Override
	public List<LineHistoryModel> getLineHistoryModel() {
		return biDao.getLineHistoryModel();
	}

}
