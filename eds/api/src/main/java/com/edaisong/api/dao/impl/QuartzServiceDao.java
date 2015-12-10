package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/**
	 * @author haichao
	 * @date 2015年12月10日 10:18:44 修改服务状态
	 * */
	@Override
	public int updateStatus(int id, int status) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("status", status);
		return getMasterSqlSessionUtil().update(
				"IQuartzServiceDao.updateStatus", map);
	}

}
