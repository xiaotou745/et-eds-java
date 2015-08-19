package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IBusinessBalanceRecordDao;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.TransDetailReq;
@Repository
public class BusinessBalanceRecordDao extends DaoBase implements IBusinessBalanceRecordDao {
	@Override
	public int insert(BusinessBalanceRecord record) {
	   int result=	getMasterSqlSessionUtil().insert("com.edaisong.api.dao.inter.IBusinessBalanceRecordDao.insert",record);
		return result;
	}
/**
 * 
 * 获取交易详情列表
 * 2015年8月4日10:37:12
 * 茹化肖 
 * 
 * */
	@Override
	public PagedResponse<BusinessBalanceRecord> getTransDetailList(TransDetailReq par) {
		PagedResponse<BusinessBalanceRecord> resp = new PagedResponse<BusinessBalanceRecord>();
		resp= getReadOnlySqlSessionUtil()
				.selectPageList("com.edaisong.api.dao.inter.IBusinessBalanceRecordDao.getTransDetailList",
						par);
		return resp;
	}

}
