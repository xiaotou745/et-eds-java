package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IBiDao;
import com.edaisong.entity.Everyday;

@Repository
public class BiDao extends DaoBase implements IBiDao {

	@Override
	public List<Everyday> queryEveryDayDao() {
		return getReadOnlySqlSessionUtil().selectList(
				"IBiDao.queryEveryDayDao");
	}

}
