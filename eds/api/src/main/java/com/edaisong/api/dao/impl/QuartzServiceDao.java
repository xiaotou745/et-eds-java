package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IQuartzServiceDao;
import com.edaisong.entity.QuartzServiceModel;

@Repository
public class QuartzServiceDao extends DaoBase implements IQuartzServiceDao {

	@Override
	public List<QuartzServiceModel> query() {
		return getReadOnlySqlSessionUtil()
				.selectList("IQuartzServiceDao.query");
	}

}
