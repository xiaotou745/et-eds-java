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

	@Override
	public PagedResponse<BusinessClientersModel> getBusinessClienters(PagedBusinessClientersReq req) {
		Map<String, Object> map = new HashMap<String, Object>();		
/*		String Where = " 1=1 ";	
		if(req.getWorkStatus() < 2){
			Where += " and c.workstatus=" + req.getWorkStatus();
		}
		if (!StringUtils.isEmpty(req.getSearch())) {
			Where += " and (c.trueName like ''%" + req.getSearch() 
					+ "%'' or c.phoneNo like ''%"+ req.getSearch()+"%'')";
		}
		Where+=" group by c.Id";*/
		
		String selectTable = " dbo.clienter c (nolock) JOIN dbo.BusinessClienterRelation (nolock) bcr ON bcr.ClienterId = c.Id AND bcr.BusinessId="
				+req.getBusinessId()+" join dbo.[order] o (nolock) on o.clienterId=c.Id ";
		
		int PageSize = 15;
		int CurrentPage = req.getCurrentPage();
		//map.put("Where", Where);
		map.put("workStatus", req.getWorkStatus());
		map.put("search", req.getSearch());
		map.put("businessId", req.getBusinessId());
		//map.put("selectTable", selectTable);
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

}
