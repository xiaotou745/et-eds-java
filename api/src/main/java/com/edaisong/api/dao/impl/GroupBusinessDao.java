package com.edaisong.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IGroupBusinessDao;

import java.util.HashMap;
import java.util.Map;

import com.edaisong.entity.Business;
import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.GroupBusinessRecharge;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessModel;
import com.edaisong.entity.req.GroupBusinessReq;
import com.edaisong.entity.req.PagedGroupBusinessReq;

@Repository
public class GroupBusinessDao extends DaoBase implements IGroupBusinessDao {
	 
	@Override
	public PagedResponse<GroupBusinessModel> getPageList(PagedGroupBusinessReq req) {
		PagedResponse<GroupBusinessModel> result = new PagedResponse<GroupBusinessModel>();
		result = getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dao.inter.IGroupBusinessDao.getPageList", req);
		return result;
	}
	@Override
	public GroupBusinessModel getSingle(GroupBusinessReq gbr) { 
		GroupBusinessModel result = new GroupBusinessModel();
		result = getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IGroupBusinessDao.getSingle", gbr);
		return result;
	}
	@Override
	public int addGroupBusiness(GroupBusiness groupBusiness) {
		// TODO Auto-generated method stub
		return getMasterSqlSessionUtil().insert("com.edaisong.api.dao.inter.IGroupBusinessDao.insert", groupBusiness);
	}

	@Override
	public int modifyGroupBusiness(GroupBusiness groupBusiness) {
		return getMasterSqlSessionUtil().update("com.edaisong.api.dao.inter.IGroupBusinessDao.updateByPrimaryKey", groupBusiness);
	}
	@Override
	public GroupBusiness getByPhoneNoAndPwd(String phoneNo, String password) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("phoneNo", phoneNo);
		paramMap.put("password", password);
		return getReadOnlySqlSessionUtil()
				.selectOne("com.edaisong.api.dao.inter.IGroupBusinessDao.getByPhoneNoAndPwd", paramMap);
	}
	@Override
	public int recharge(int groupID, double amount) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groupID", groupID);
		paramMap.put("amount", amount);
		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dao.inter.IGroupBusinessDao.recharge", paramMap);
	} 
	@Override
	public GroupBusiness select(int groupID) {
		return getMasterSqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IGroupBusinessDao.select",
				groupID);
	}
}
