package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IBiDao;
import com.edaisong.entity.Everyday;
import com.edaisong.entity.domain.LineHistoryModel;

@Repository
public class BiDao extends DaoBase implements IBiDao {

	@Override
	public List<Everyday> queryEveryDayDao() {
		return getReadOnlySqlSessionUtil().selectList(
				"IBiDao.queryEveryDayDao");
	}
	/**
	 * 查询上线记录
	 */
	@Override
	public List<LineHistoryModel> getLineHistoryModel() {
		return getReadOnlySqlSessionUtil().selectList(
				"IBiDao.getLineHistoryModel");
	}

}
