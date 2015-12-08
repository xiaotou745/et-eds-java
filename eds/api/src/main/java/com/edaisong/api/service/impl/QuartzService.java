package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IQuartzServiceDao;
import com.edaisong.api.service.inter.IQuartzService;
import com.edaisong.entity.QuartzServiceModel;

@Service
public class QuartzService implements IQuartzService {
	@Autowired
	IQuartzServiceDao quartzServiceDao;

	@Override
	public List<QuartzServiceModel> query() {
		return quartzServiceDao.query();
	}

}
