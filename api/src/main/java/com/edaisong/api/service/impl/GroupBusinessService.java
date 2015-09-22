package com.edaisong.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.dao.inter.IGroupBusinessBalanceDao;
import com.edaisong.api.dao.inter.IGroupBusinessDao;
import com.edaisong.api.service.inter.IGroupBusinessLogService;
import com.edaisong.api.service.inter.IGroupBusinessRechargeService;
import com.edaisong.api.service.inter.IGroupBusinessService;
import com.edaisong.core.security.MD5Util;
import com.edaisong.entity.Business;
import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.GroupBusinessLog;
import com.edaisong.entity.GroupBusinessRecharge;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.common.ResponseCode;
import com.edaisong.entity.domain.GroupBusinessBalanceRecord;
import com.edaisong.entity.domain.GroupBusinessModel;
import com.edaisong.entity.req.ClienterUnfreezeReq;
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
	@Autowired
	IGroupBusinessLogService groupBusinessLogService;
	@Autowired
	IGroupBusinessRechargeService groupBusinessRechargeService;
	@Override
	public PagedResponse<GroupBusinessModel> getPageList(
			PagedGroupBusinessReq req) {
		// TODO Auto-generated method stub
		return groupBusinessDao.getPageList(req);
	}

	@Override
	public GroupBusiness login(String phoneNo, String password) {
		String pwd = MD5Util.MD5(password);
		return groupBusinessDao.getByPhoneNoAndPwd(phoneNo, pwd);
	}

	@Override
	public GroupBusinessModel getSingle(GroupBusinessReq gbr) {
		// TODO Auto-generated method stub
		return groupBusinessDao.getSingle(gbr);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int addGroupBusiness(GroupBusiness groupBusiness) {
		int groupResult = groupBusinessDao.addGroupBusiness(groupBusiness);
		if (groupResult > 0) {
			GroupBusinessLog gbl = new GroupBusinessLog();
			gbl.setGroupBusinessId(groupBusiness.getId());
			gbl.setOptname(groupBusiness.getCreatename());
			gbl.setRemark("添加集团商户");
			int logResult = groupBusinessLogService.insert(gbl); //插入日志
			if (logResult <= 0) {
				throw new RuntimeException("添加集团运行错误");
			}
		}
		return groupResult;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, timeout = 30)
	public int modifyGroupBusiness(GroupBusiness groupBusiness) {
		String remark = getModifyGroupBusinessRemark(groupBusiness);
		int mResult = groupBusinessDao.modifyGroupBusiness(groupBusiness);  
		if(mResult>0){
			GroupBusinessLog gbl = new GroupBusinessLog();
			gbl.setGroupBusinessId(groupBusiness.getId());
			gbl.setOptname(groupBusiness.getModifyname());
			gbl.setRemark(remark);
			int logResult = groupBusinessLogService.insert(gbl); //插入日志
			if (logResult <= 0) {
				throw new RuntimeException("修改集团运行错误");
			}
		}
		return groupBusinessDao.modifyGroupBusiness(groupBusiness);
	}
	private String getModifyGroupBusinessRemark(GroupBusiness gb){
		StringBuilder sbRemark = new StringBuilder("修改");
		GroupBusinessReq gbr = new GroupBusinessReq();
		gbr.setId(gb.getId()); 
		 GroupBusinessModel gbm=groupBusinessDao.getSingle(gbr);
		 if(gbm!=null){
			 if(!gbm.getGroupbusiname().trim().equals(gb.getGroupbusiname().trim())){
				 sbRemark.append("集团名称"+gbm.getGroupbusiname()+"修改为"+gb.getGroupbusiname()+";");
			 }
			 if(!gbm.getLoginname().trim().equals(gb.getLoginname().trim())){
				 sbRemark.append("登录名称"+gbm.getLoginname()+"修改为"+gb.getLoginname()+";");
			 }
			 if(!gb.getPassword().trim().equals("")){
				 if(!gbm.getPassword().trim().equals(gb.getPassword().trim())){
					 sbRemark.append("密码;");
				 }
			 }
			 if(gbm.getIsAllowOverdraft() != gb.getIsAllowOverdraft()){
				 sbRemark.append("是否允许透支修改为"+(gb.getIsAllowOverdraft() == 1?"是":"否")+";");
			 }
		 }
		 return sbRemark.toString();
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
		return groupBusinessBalanceDao
				.getGroupBusinessBalanceRecordForExport(req);
	}

	@Override
	public GroupBusinessRecharge getRechargeDetail(String orderNo) {
		 return groupBusinessRechargeService.getByOrderNo(orderNo);
	}
}
