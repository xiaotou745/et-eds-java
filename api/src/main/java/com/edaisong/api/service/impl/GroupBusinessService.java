package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IGroupBusinessBalanceDao;
import com.edaisong.api.dao.inter.IGroupBusinessDao;
import com.edaisong.api.service.inter.IGroupBusinessService;
import com.edaisong.entity.Business;
import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseCode;
import com.edaisong.entity.domain.GroupBusinessBalanceRecord;
import com.edaisong.entity.domain.GroupBusinessModel;
import com.edaisong.entity.req.GroupBusinessReq;
import com.edaisong.entity.req.PagedGroupBusinessReq;
import com.edaisong.entity.req.PagedGroupBussinessBalanceReq;
import com.edaisong.entity.resp.BusinessLoginResp;
 

@Service
public class GroupBusinessService implements IGroupBusinessService {
	@Autowired 
	IGroupBusinessDao groupBusinessDao;
	@Autowired 
	IGroupBusinessBalanceDao groupBusinessBalanceDao;
	
	@Override
	public PagedResponse<GroupBusinessModel> getPageList(
		PagedGroupBusinessReq req) {
	// TODO Auto-generated method stub
		return groupBusinessDao.getPageList(req);
	}
	@Override
	public GroupBusiness login(String phoneNo, String password) {
		return groupBusinessDao.getByPhoneNoAndPwd(phoneNo, password);
	} 
	@Override
	public GroupBusinessModel getSingle(GroupBusinessReq gbr) {
		// TODO Auto-generated method stub
		return groupBusinessDao.getSingle(gbr);
	}

	@Override
	public int addGroupBusiness(GroupBusiness groupBusiness) {
		// TODO Auto-generated method stub
		return groupBusinessDao.addGroupBusiness(groupBusiness);
	}

	@Override
	public int modifyGroupBusiness(GroupBusiness groupBusiness) {
		return groupBusinessDao.modifyGroupBusiness(groupBusiness);
	}
	@Override
	public PagedResponse<GroupBusinessBalanceRecord> getGroupBusinessRecord(
			PagedGroupBussinessBalanceReq req) {
		// TODO Auto-generated method stub
		return groupBusinessBalanceDao.getGroupBusinessRecord(req);
	}
	@Override
	public List<GroupBusinessBalanceRecord> getGroupBusinessBalanceRecordForExport(
			PagedGroupBussinessBalanceReq req) {
		 return groupBusinessBalanceDao.getGroupBusinessBalanceRecordForExport(req);
	}
}
