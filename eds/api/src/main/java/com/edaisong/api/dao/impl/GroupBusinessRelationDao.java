package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IGroupBusinessRelationDao;
import com.edaisong.entity.GroupBusinessRelation;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessRelationModel;
import com.edaisong.entity.req.BusinessBindOptionReq;
import com.edaisong.entity.req.PagedBizBindsReq;

/**
 *
 * @author  pengyi 
 * @date 2015年9月9日 上午10:52:32
 * @version 1.0
 * @parameter
 * @since
 */
@Repository
public class GroupBusinessRelationDao extends DaoBase implements IGroupBusinessRelationDao{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(GroupBusinessRelation record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(GroupBusinessRelation record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GroupBusinessRelation selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(GroupBusinessRelation record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(GroupBusinessRelation record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PagedResponse<GroupBusinessRelationModel> getBusinessBindList(PagedBizBindsReq req) {
		return getReadOnlySqlSessionUtil()
				.selectPageList("IGroupBusinessRelationDao.getBusinessBindList", req);
	}

	@Override
	public boolean removeBusinessBind(BusinessBindOptionReq req) {
		return getMasterSqlSessionUtil()
				.update("IGroupBusinessRelationDao.removeBusinessBind", req) > 0;
	}

	public boolean addBusinessBind(BusinessBindOptionReq req){
		return getMasterSqlSessionUtil()
				.update("IGroupBusinessRelationDao.addBusinessBind", req) > 0;
	}

	@Override
	public boolean checkHaveBind(BusinessBindOptionReq req) {
		return ((int)getReadOnlySqlSessionUtil()
				.selectOne("IGroupBusinessRelationDao.checkHaveBind", req)) > 0;
	}

	@Override
	public PagedResponse<GroupBusinessRelationModel> getBusinessList(PagedBizBindsReq req) {
		return getReadOnlySqlSessionUtil()
				.selectPageList("IGroupBusinessRelationDao.getBusinessList", req);
	}

	@Override
	public List<GroupBusinessRelationModel> getBusinessListForStr(int groupid) {
		return getReadOnlySqlSessionUtil()
				.selectList("IGroupBusinessRelationDao.getBusinessListForStr", groupid);
	}
}
