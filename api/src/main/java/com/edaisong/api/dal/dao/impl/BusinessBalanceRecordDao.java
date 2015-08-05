package com.edaisong.api.dal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IBusinessBalanceRecordDao;
import com.edaisong.core.common.ParseHelper;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.TransDetailReq;
@Repository
public class BusinessBalanceRecordDao extends DaoBase implements IBusinessBalanceRecordDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(BusinessBalanceRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(BusinessBalanceRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BusinessBalanceRecord selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(BusinessBalanceRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(BusinessBalanceRecord record) {
		// TODO Auto-generated method stub
		return 0;
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
		Map<String, Object> map = new HashMap<String, Object>();	
		
		String Where = " 1=1 ";		
		//开始时间
		if (!StringUtils.isEmpty(par.getStartDate())) {
			Where += "  AND bbr.OperateTime >'"+par.getStartDate()+"' ";
		}
		//结束时间
		if (!StringUtils.isEmpty(par.getEndDate())) {
			Where += "  AND bbr.OperateTime <'"+par.getStartDate()+"' ";
		}
		//交易类型
		if (par.getTransType()!="0") {
			Where += "  AND bbr.RecordType ="+par.getTransType()+" ";
		}	
		//选择订单号 
		if (par.getNumType()=="1"&&!StringUtils.isEmpty(par.getNumString())) {
			Where += "  AND (RecordType=1 OR RecordType=2 OR RecordType=8) AND bbr.RelationNo='"+par.getNumString()+"' ";
		}
		//选择流水号
		if (par.getNumType()=="2"&&!StringUtils.isEmpty(par.getNumString())) {
			Where += "  AND (RecordType=9 OR RecordType=1) AND bbr.RelationNo='"+par.getNumString()+"' ";
		}
		//过滤商户可以看得流水
		Where +=" AND bbr.BusinessId="+par.getBusinessid()+" AND RecordType IN (1,2,6,8,9,11) ";
		//Where +=" AND RecordType IN (1,2,6,8,9,11) ";
		
		int PageSize = 15;
		int CurrentPage = par.getCurrentPage();
		map.put("Where", Where);
		map.put("TotalRecord", 0);
		map.put("TotalPage", 0);
		map.put("PageSize", PageSize);
		map.put("CurrentPage", CurrentPage);
		List<BusinessBalanceRecord> list = getReadOnlySqlSessionUtil()
				.selectList("com.edaisong.api.dal.dao.inter.IBusinessBalanceRecordDao.getTransDetailList",
						map);
		
		PagedResponse<BusinessBalanceRecord> resp = new PagedResponse<BusinessBalanceRecord>();		
		resp.setResultList(list);
		resp.setPageSize(PageSize);
		resp.setCurrentPage(CurrentPage);
		resp.setTotalRecord(ParseHelper.ToInt(map.get("TotalRecord"), 0));
		resp.setTotalPage(ParseHelper.ToInt(map.get("TotalPage"), 0));
		return resp;
	}

}
