package com.edaisong.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IGroupBusinessBindOptionLogDao;
import com.edaisong.entity.GroupBusinessBindOptionLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessBindOptionLogModel;
import com.edaisong.entity.req.PagedBusinessBindLogReq;

/**
 *
 * @author  pengyi 
 * @date 2015年9月10日 上午9:56:44
 * @version 1.0
 * @parameter
 * @since
 */
@Repository
public class GroupBusinessBindOptionLogDao extends DaoBase implements IGroupBusinessBindOptionLogDao{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(GroupBusinessBindOptionLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(GroupBusinessBindOptionLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GroupBusinessBindOptionLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(GroupBusinessBindOptionLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(GroupBusinessBindOptionLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PagedResponse<GroupBusinessBindOptionLogModel> getBusinessBindLogList(PagedBusinessBindLogReq req) {
		return getReadOnlySqlSessionUtil().selectPageList("com.edaisong.api.dao.inter.IGroupBusinessBindOptionLogDao.getBusinessBindLogList", req);
	}

}
