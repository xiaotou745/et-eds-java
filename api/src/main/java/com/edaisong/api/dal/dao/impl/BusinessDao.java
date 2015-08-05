package com.edaisong.api.dal.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IBusinessDao;
import com.edaisong.entity.Business;
import com.edaisong.entity.BusinessLoginLog;
import com.edaisong.entity.BusinessOptionLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessDetailModel;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.domain.BusinessModifyModel;
import com.edaisong.entity.req.PagedBusinessReq;

@Repository
public class BusinessDao extends DaoBase implements IBusinessDao {

	@Override
	public PagedResponse<BusinessModel> getBusinessList(PagedBusinessReq req) {
		PagedResponse<BusinessModel> model = getReadOnlySqlSessionUtil()
				.selectPageList(
						"com.edaisong.api.dal.dao.inter.IBusinessDao.getBusinessList",
						req);
		return model;
	}

	@Override
	public BusinessDetailModel getBusinessDetailByID(int businessID) {
		return getReadOnlySqlSessionUtil()
				.selectOne(
						"com.edaisong.api.dal.dao.inter.IBusinessDao.getBusinessDetailByID",
						businessID);
	}

	@Override
	public List<BusinessOptionLog> getOpLogByBusinessID(int businessID) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"com.edaisong.api.dal.dao.inter.IBusinessDao.getOpLogByBusinessID",
						businessID);
	}

	@Override
	public int modifyBusiness(BusinessModifyModel detailModel) {
		return getMasterSqlSessionUtil().update(
				"com.edaisong.api.dal.dao.inter.IBusinessDao.modifyBusiness",
				detailModel);
	}

	@Override
	public Business login(String phoneNo, String password) {
		Map<String, Object> paramMap = new HashedMap();

		return getReadOnlySqlSessionUtil().selectOne("com.edaisong.api.dal.dao.inter.IBusinessDao.getBusinessByPhoneNoAndPwd",paramMap);
	}

	@Override
	public boolean addLoginLog(BusinessLoginLog log) {
		return getMasterSqlSessionUtil().insert("com.edaisong.api.dal.dao.inter.IBusinessDao.addLogingLog",log) > 0;
	}
/**
	 * 更新 商户 余额，可提现余额
	 * 
	 * @param money
	 *            金额
	 * @param businessId
	 *            商户id
	 * @Date 20150804
	 * @param business
	 * @return
	 */
	@Override
	public int updateForWithdraw(BigDecimal money, int businessId) {
		Map<String, Object> parasMap = new HashMap();
		parasMap.put("Money", money);
		parasMap.put("Id", businessId);
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dal.dao.inter.IBusinessDao.updateForWithdraw",
						parasMap);

	}
	@Override
	public Business getBusinessById(int businessId) { 
		return getReadOnlySqlSessionUtil().selectOne("com.edaisong.api.dal.dao.inter.IBusinessDao.getBusinessById",businessId);
}
}
