package com.edaisong.api.dao.impl;

import org.springframework.stereotype.Service;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IOptLogDao;
import com.edaisong.entity.OptLog;

@Service
public class OptLogDao extends DaoBase implements IOptLogDao {

	/**
	* @author haichao
	* @date 2015年11月25日 13:02:00
	* 写操作日志
	* */
	@Override
	public void insert(OptLog req) {
		getMasterSqlSessionUtil().insert("IOptLog.insert",req);
	}

}
