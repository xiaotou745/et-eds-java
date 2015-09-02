package com.edaisong.api.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IClienterDao;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.Clienter;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessClientersModel;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.ClienterReq;
import com.edaisong.entity.req.PagedBusinessClientersReq;
import com.edaisong.entity.req.PagedClienterSearchReq;




@Repository
public class ClienterDao extends DaoBase implements IClienterDao {

	@Override
	public int updateByPrimaryKeySelective(Clienter record) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", record.getId());
		paramMap.put("status", record.getStatus());	
		//其它的暂时没有写
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IClienterDao.updateByPrimaryKeySelective",
						paramMap);
	}

	@Override
	public int updateByPrimaryKey(Clienter record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int updateMoneyById(ClienterOptionReq record) {
	    
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("clienterId", record.getClienterId());
		paramMap.put("optName", record.getOptName());			
		paramMap.put("remark", record.getRemark());	
		paramMap.put("rechargeAmount", record.getRechargeAmount().doubleValue());
		paramMap.put("recordType", 8);	
		paramMap.put("withwardId", 0);	
		
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IClienterDao.updateMoneyById",
						paramMap);
	}	
	
	@Override
	public PagedResponse<ClienterModel> query(ClienterReq req) {

		Map<String, Object> map = new HashMap<String, Object>();		
		String Where = " 1=1 ";		
		
		if (!StringUtils.isEmpty(req.getTrueName())) {
			Where += " and c.trueName like '%" + req.getTrueName() + "%'";
		}
		if (req.getStatus() !=null && req.getStatus().intValue()>-1) {
			Where += " and c.status= '" + req.getStatus() + "'";
		}		
		if (!StringUtils.isEmpty(req.getPhoneNo())) {
			Where += " and c.phoneNo like '%" + req.getPhoneNo() + "%'";
		}
		if (!StringUtils.isEmpty(req.getRecommendPhone())) {
			Where += " and cl.PhoneNo like '%" + req.getRecommendPhone() + "%'";
		}
		if (req.getDeliveryCompanyId() >0) {
			Where += " and c.DeliveryCompanyId = '" + req.getDeliveryCompanyId() + "'";
		}
		if(req.getCode()>0) {
			Where += " and c.CityId = '" + req.getCode() + "'";
		}
			
		
		int PageSize = 15;
		int CurrentPage = req.getCurrentPage();
		map.put("Where", Where);
		map.put("TotalRecord", 0);
		map.put("TotalPage", 0);
		map.put("PageSize", PageSize);
		map.put("CurrentPage", CurrentPage);
		List<ClienterModel> list = getMasterSqlSessionUtil()
				.selectList("com.edaisong.api.dao.inter.IClienterDao.query",
						map);
		
		PagedResponse<ClienterModel> resp = new PagedResponse<ClienterModel>();		
		resp.setResultList(list);
		resp.setPageSize(PageSize);
		resp.setCurrentPage(CurrentPage);
		resp.setTotalRecord(ParseHelper.ToInt(map.get("TotalRecord"), 0));
		resp.setTotalPage(ParseHelper.ToInt(map.get("TotalPage"), 0));
		return resp;
	}

	/**
	 * 获得商家下的所有骑士 ,包含骑士已接单的数量
	 * @author pengyi
	 * @date 20150901
	 */
	@Override
	public PagedResponse<BusinessClientersModel> getBusinessClienters(PagedBusinessClientersReq req) {
		Map<String, Object> map = new HashMap<String, Object>();				
		int PageSize = 15;
		int CurrentPage = req.getCurrentPage();
		map.put("workStatus", req.getWorkStatus());
		map.put("search", req.getSearch());
		map.put("businessId", req.getBusinessId());
		map.put("TotalRecord", 0);
		map.put("TotalPage", 0);
		map.put("PageSize", PageSize);
		map.put("CurrentPage", CurrentPage);
		List<BusinessClientersModel> list = getReadOnlySqlSessionUtil()
				.selectList("com.edaisong.api.dao.inter.IClienterDao.getBusinessClienters",
						map);
		
		PagedResponse<BusinessClientersModel> resp = new PagedResponse<BusinessClientersModel>();		
		resp.setResultList(list);
		resp.setPageSize(PageSize);
		resp.setCurrentPage(CurrentPage);
		resp.setTotalRecord(ParseHelper.ToInt(map.get("TotalRecord"), 0));
		resp.setTotalPage(ParseHelper.ToInt(map.get("TotalPage"), 0));
		return resp;
	}

    /**
     * 更新骑士余额
     * @param amount
     * @param clienterId
     *  @Date 20150831
	 * @param business
     */
	@Override
	public int updateCAccountBalance(Double amount, int clienterId) {
		Map<String, Object> parasMap = new HashMap();
		parasMap.put("Money", amount);
		parasMap.put("Id", clienterId);
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IClienterDao.updateCAccountBalance",
						parasMap);
	}

	 /**
     * 更新骑士余额
     * @param amount
     * @param clienterId
     *  @Date 20150831
	 * @param business
     */
	@Override
	public int updateCAllowWithdrawPrice(Double amount, int clienterId) {
		Map<String, Object> parasMap = new HashMap();
		parasMap.put("Money", amount);
		parasMap.put("Id", clienterId);
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IClienterDao.updateCAllowWithdrawPrice",
						parasMap);
	}
	/**
	 * 更新骑士余额和可提现金额
	 * @author CaoHeYang
	 * @param amount
	 * @param clienterId
	 * @date 20150831
	 * @return
	 */
	@Override
	public int updateCBalanceAndWithdraw(Double amount, int clienterId) {
		Map<String, Object> parasMap = new HashMap();
		parasMap.put("Money", amount);
		parasMap.put("Id", clienterId);
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IClienterDao.updateCBalanceAndWithdraw",
						parasMap);
	}

	@Override
	public boolean updateClienterIsBind(int clienterId, int isBind) {
		Map<String, Object> parasMap = new HashMap();
		parasMap.put("clienterId", clienterId);
		parasMap.put("isBind", isBind);
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IClienterDao.updateClienterIsBind",
						parasMap) > 0;
	}

	/**
	 * 获得骑士和绑定信息列表
	 * @author pengyi
	 * @date 20150901
	 */
	@Override
	public PagedResponse<ClienterModel> getClienterBindInfoList(PagedClienterSearchReq req) {
		return getReadOnlySqlSessionUtil()
				.selectPageList("com.edaisong.api.dao.inter.IClienterDao.getClienterBindInfoList",
						req);
	}
}
