package com.edaisong.api.dal.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IGroupDao;
import com.edaisong.entity.Group;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.req.GroupReq;

@Repository
public class GroupDao extends DaoBase implements IGroupDao {
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	 @Override
	public int insert(Group record) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groupname", record.getGroupname());
		paramMap.put("createname", record.getCreatename());
		paramMap.put("createtime", new Date());
		paramMap.put("modifyname", "");
		paramMap.put("modifytime", new Date());
		paramMap.put("isvalid", 1);
		paramMap.put("ismodifybind", 0);

		return getMasterSqlSessionUtil().insert(
				"com.edaisong.api.dal.dao.inter.IGroupDao.insert", paramMap);
	}

	@Override
	public int insertSelective(Group record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Group selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Group record) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", record.getId());
		if (record.getGroupname() != "" && record.getGroupname() != null)
			paramMap.put("groupname", record.getGroupname());
		if (record.getIsvalid() != null)
			paramMap.put("isvalid", record.getIsvalid());

		return getMasterSqlSessionUtil()
				.update("com.edaisong.api.dal.dao.inter.IGroupDao.updateByPrimaryKeySelective",
						paramMap);

	}

	@Override
	public int updateByPrimaryKey(Group record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GroupModel> getGroupListByID(Long id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		List<GroupModel> list = getMasterSqlSessionUtil().selectList(
				"com.edaisong.api.dal.dao.inter.IGroupDao.getGroupListByID",
				paramMap);
		return list;

	}

	@Override
	public List<GroupModel> getGroupList(GroupReq req) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("groupname", req.getGroupName());
		paramMap.put("appkey", req.getAppKey());
		List<GroupModel> list = getMasterSqlSessionUtil().selectList(
				"com.edaisong.api.dal.dao.inter.IGroupDao.getGroupList",
				paramMap);
		return list;
	}

}
