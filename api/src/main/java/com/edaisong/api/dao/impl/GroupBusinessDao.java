package com.edaisong.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IGroupBusinessDao;
import java.util.HashMap;
import java.util.Map;import com.edaisong.entity.Business;
import com.edaisong.entity.GroupBusiness;
import com.edaisong.entity.req.PagedGroupBusinessReq;

@Repository
public class GroupBusinessDao implements IGroupBusinessDao {
	@Override
	public int insert(GroupBusiness record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GroupBusiness selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKey(GroupBusiness record) {
		// TODO Auto-generated method stub
		return 0;
	}

@Override
	public PagedResponse<GroupBusinessModel> getPageList(
			PagedGroupBusinessReq req) {
		PagedResponse<GroupBusinessModel> result = new PagedResponse<GroupBusinessModel>();
		result = getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dao.inter.IGroupBusinessDao.getPageList", req);
		return result;
	}
@Override
	public GroupBusiness getByPhoneNoAndPwd(String phoneNo, String password) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("phoneNo", phoneNo);
		paramMap.put("password", password);
		return getReadOnlySqlSessionUtil()
				.selectOne("com.edaisong.api.dao.inter.IGroupBusinessDao.login", paramMap);
	}
}
