package com.edaisong.api.dao.impl;

import java.util.List;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IBIDao;
import com.edaisong.entity.Everyday;

public class BIDao extends DaoBase implements IBIDao {

	@Override
	public List<Everyday> queryEveryDayDao() {
		return getReadOnlySqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IBIDao.queryEveryDayDao");
	}

}
