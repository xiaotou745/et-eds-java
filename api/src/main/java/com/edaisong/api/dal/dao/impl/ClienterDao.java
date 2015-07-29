package com.edaisong.api.dal.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;












import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IClienterDao;
import com.edaisong.core.common.ParseHelper;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.Account;
import com.edaisong.entity.Clienter;
import com.edaisong.entity.common.ResponsePageList;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.req.AccountReq;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.ClienterReq;
import com.edaisong.entity.resp.AccountResp;
import com.edaisong.entity.resp.ClienterResp;


@Repository
public class ClienterDao extends DaoBase implements IClienterDao {

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Clienter record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Clienter record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Clienter selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Clienter record) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", record.getId());
		paramMap.put("status", record.getStatus());	
		//其它的暂时没有写
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dal.dao.inter.IClienterDao.updateByPrimaryKeySelective",
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
				.update("com.edaisong.api.dal.dao.inter.IClienterDao.updateMoneyById",
						paramMap);
	}	
	
	@Override
	public ResponsePageList<ClienterModel> query(ClienterReq req) {

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
				.selectList("com.edaisong.api.dal.dao.inter.IClienterDao.query",
						map);
		
		ResponsePageList<ClienterModel> resp = new ResponsePageList<ClienterModel>();		
		resp.setResultList(list);
		resp.setPageSize(PageSize);
		resp.setCurrentPage(CurrentPage);
		resp.setTotalRecord(ParseHelper.ToInt(map.get("TotalRecord"), 0));
		resp.setTotalPage(ParseHelper.ToInt(map.get("TotalPage"), 0));
		return resp;
	}

}
