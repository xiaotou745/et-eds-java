package com.edaisong.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IOrderGrabDao;
import com.edaisong.entity.OrderGrab;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.FastOrderDetail;
import com.edaisong.entity.domain.FastOrderExportModel;
import com.edaisong.entity.domain.FastOrderMapDetail;
import com.edaisong.entity.domain.FastOrderModel; 
import com.edaisong.entity.domain.MyOrderGrabCModel;
import com.edaisong.entity.domain.QueryOrder;
import com.edaisong.entity.req.HadFinishOrderReq;
import com.edaisong.entity.req.MyOrderGrabCReq;
import com.edaisong.entity.req.OrderGrabDetailCReq;
import com.edaisong.entity.req.PagedFastOrderSearchReq;
import com.edaisong.entity.resp.HadFinishOrderResp;
import com.edaisong.entity.resp.MyOrderGrabCResp; 
import com.edaisong.entity.resp.MyOrderGrabDetailCResp;
@Repository
public class OrderGrabDao extends DaoBase implements IOrderGrabDao {

	@Override
	public int insert(OrderGrab record) {
		return getMasterSqlSessionUtil().insert(
				"IOrderGrabDao.insert", record);
	}
	@Override
	public int deleteById(Long id) {
		return getMasterSqlSessionUtil().delete(
				"IOrderGrabDao.deleteById", id);
	}
	
	@Override
	public int updateByPrimaryKeySelective(OrderGrab record)
	{
		return getMasterSqlSessionUtil().update(
				"IOrderGrabDao.updateByPrimaryKeySelective", record);
	}	

	@Override
	public OrderGrab selectByPrimaryKeyWrite(Integer id)
	{
		return getMasterSqlSessionUtil().selectOne(
				"IOrderGrabDao.selectByPrimaryKey", id);		
	}
		
	@Override
	public FastOrderDetail selectById(Long id) {
		return getReadOnlySqlSessionUtil().selectOne(
				"IOrderGrabDao.selectById", id);
	}

	@Override
	public PagedResponse<FastOrderModel> query(PagedFastOrderSearchReq req) {
		return getReadOnlySqlSessionUtil().selectPageList(
				"IOrderGrabDao.query", req);
	}

	@Override
	public List<FastOrderMapDetail> getMapDetailById(Long id) {
		return getReadOnlySqlSessionUtil()
				.selectList(
						"IOrderGrabDao.getMapDetailById",
						id);
	} 
	/*
	 * 获取我的任务
	 * wangchao
	 */
	@Override
	public List<MyOrderGrabCModel> getMyOrderGrabC(
			MyOrderGrabCReq myOrderGrabCReq) {
		return getReadOnlySqlSessionUtil().selectList(
				"IOrderGrabDao.getMyOrderGrabC", myOrderGrabCReq);
	}
	/*
	 * 获取我的任务详情
	 * wangchao
	 */
	@Override
	public MyOrderGrabDetailCResp getMyOrderGrabDetailC(
			OrderGrabDetailCReq orderGrabDetailCReq) { 
		return getReadOnlySqlSessionUtil().selectOne("IOrderGrabDao.getMyOrderGrabDetailC", orderGrabDetailCReq);
	}

	@Override
	public int insertSelective(OrderGrab record) {
	
		return getMasterSqlSessionUtil().insert(
				"IOrderGrabDao.insertSelective", record);
	}

	@Override
	public List<FastOrderExportModel> exportOrder(PagedFastOrderSearchReq req) {
		return getReadOnlySqlSessionUtil().selectList("IOrderGrabDao.exportOrder", req);
	}
	@Override
	public MyOrderGrabCResp getMyOrderGrabCTotalInfo(
			MyOrderGrabCReq myOrderGrabCReq) {
		return getReadOnlySqlSessionUtil().selectOne("IOrderGrabDao.getMyOrderGrabCTotalInfo", myOrderGrabCReq);
	}
	@Override
	public List<MyOrderGrabCModel> getHadFinishOrderC(HadFinishOrderReq para) {
		return getReadOnlySqlSessionUtil().selectList(
				"IOrderGrabDao.getHadFinishOrderC", para);
	}
	@Override
	public HadFinishOrderResp getHadFinishOrderCTotalInfo(HadFinishOrderReq para) {
		return getReadOnlySqlSessionUtil().selectOne("IOrderGrabDao.getHadFinishOrderCTotalInfo", para);
	} 
}
