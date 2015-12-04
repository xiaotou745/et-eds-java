package com.edaisong.api.dao.impl;

import org.springframework.stereotype.Repository;
import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.ITestDao;

@Repository
public class TestDao extends DaoBase implements ITestDao {

	@Override
	public void insert(String val) {
		getMasterSqlSessionUtil().update("ITestDao.insertTest", val);
	}

}
