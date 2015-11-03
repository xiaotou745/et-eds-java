package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IBusinessClienterRelationDao;
import com.edaisong.entity.BusinessClienterRelation;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessClienterRelationModel;
import com.edaisong.entity.domain.ServiceClienters;
import com.edaisong.entity.req.ClienterBindOptionReq;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedGetMyServiceClientersReq;

@Repository
public class BusinessClienterRelationDao extends DaoBase implements IBusinessClienterRelationDao{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(BusinessClienterRelation record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(BusinessClienterRelation record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BusinessClienterRelation selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(BusinessClienterRelation record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(BusinessClienterRelation record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 获得商户绑定的骑士数量
	 * @author pengyi
	 * @date 20150831
	 */
	@Override
	public int getBusinessBindClienterQty(int businessId) {
		return getReadOnlySqlSessionUtil()
				.selectOne("com.edaisong.api.dao.inter.IBusinessClienterRelationDao.getBusinessBindClienterQty", businessId);
	}
	
	/**
	 * 获得骑士绑定的商户数量
	 * @author pengyi
	 * @date 20150901
	 */
	@Override
	public int getClienterBindBusinessQty(int clienterId) {
		return getReadOnlySqlSessionUtil()
				.selectOne("com.edaisong.api.dao.inter.IBusinessClienterRelationDao.getClienterBindBusinessQty", clienterId);
	}

	/**
	 * 获得商户绑定的骑士列表
	 * @author pengyi
	 * @date 20150831
	 */
	@Override
	public PagedResponse<BusinessClienterRelationModel> getBusinessClienterRelationList(PagedCustomerSearchReq req) {
		return getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dao.inter.IBusinessClienterRelationDao.getBusinessClienterRelationList", req);
	}

	@Override
	public boolean modifyClienterBind(ClienterBindOptionReq req) {
		return getMasterSqlSessionUtil().update(
				"com.edaisong.api.dao.inter.IBusinessClienterRelationDao.modifyClienterBind", req) > 0;
	}

	@Override
	public boolean removeclienterbind(ClienterBindOptionReq req) {
		return getMasterSqlSessionUtil().update(
				"com.edaisong.api.dao.inter.IBusinessClienterRelationDao.removeclienterbind", req) > 0;
	}

	/**
	 * 确实骑士是否已绑定商家
	 * @author pengyi
	 * @date 20150901
	 */
	@Override
	public boolean checkHaveBind(ClienterBindOptionReq req) {
		return ((int)getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IBusinessClienterRelationDao.checkHaveBind", req)) > 0;
	}
	
	/**
	 * 添加骑士绑定
	 * @author pengyi
	 * @date 20150901
	 * @param req
	 * @return
	 */
	@Override
	public boolean addClienterBind(ClienterBindOptionReq req){
		return getMasterSqlSessionUtil().insert(
				"com.edaisong.api.dao.inter.IBusinessClienterRelationDao.addClienterBind", req) > 0;
	}

	@Override
	public BusinessClienterRelation getDetails(int businessId, int clienterId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("businessId", businessId);
		map.put("clienterId", clienterId);
		return getReadOnlySqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IBusinessClienterRelationDao.getDetails", map);
	}
	/**
	 * 商戶端 我的骑士
	 * @version 20151103
	 * @author CaoHeYang
	 * @date 20151103
	 * @param req
	 * @return
	 */
	@Override
	public List<ServiceClienters> getMyServiceClienters(PagedGetMyServiceClientersReq req) {
		PagedResponse<ServiceClienters> lists= getMasterSqlSessionUtil().selectPageList(
				"com.edaisong.api.dao.inter.IBusinessClienterRelationDao.getMyServiceClienters", req);
		return lists.getResultList();
	}
}
