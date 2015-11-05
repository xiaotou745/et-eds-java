package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IOrderChildDao;
import com.edaisong.api.dao.inter.IOrderDao;
import com.edaisong.entity.Account;
import com.edaisong.entity.Order;
import com.edaisong.entity.OrderChild;
import com.edaisong.entity.OrderGrabChild;
import com.edaisong.entity.OrderOther;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.OrderDetailBusiness;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.OrderGrabReq;
import com.edaisong.entity.req.PagedOrderSearchReq;

@Repository
public class OrderChildDao extends DaoBase implements IOrderChildDao {	
	
	@Override
	public int insert(OrderChild record)
	{
		return getMasterSqlSessionUtil().insert(
				"com.edaisong.api.dao.inter.IOrderChildDao.insertSelective", record);
	}	
	
	@Override
	public int insertList(List<OrderChild> record) {
		return getMasterSqlSessionUtil().insert(
				"com.edaisong.api.dao.inter.IOrderChildDao.insertList", record);
	}
	
	@Override
	public int updateByPrimaryKeySelective(OrderChild record)
	{
		return getMasterSqlSessionUtil().update(
				"com.edaisong.api.dao.inter.IOrderChildDao.updateByPrimaryKeySelective", record);
	}
	
	@Override
	public  int updateList(List<OrderChild> record)
	{
		return getMasterSqlSessionUtil().update(
				"com.edaisong.api.dao.inter.IOrderChildDao.updateList", record);
	}
    /**
     * 取消订单 （取消前一天快单发单且未被抢单的子订单时）
     * @param 日期
     * @author 胡灵波
     * @Date 2015年11月5日 11:40:37
     * @return
     */
	@Override
	public List<Integer> updateCancel(OrderChild record)
	{
		return getMasterSqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IOrderChildDao.updateCancel", record);
	}
	
	@Override
	 public List<Integer>  updateGradOne(OrderGrabReq record)
	 {
		return getMasterSqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IOrderChildDao.updateGradOne", record);
	 }
		
	@Override
	public	List<Integer>  updateGradTwo(OrderGrabReq record)
	{
		return getMasterSqlSessionUtil().selectList(
				"com.edaisong.api.dao.inter.IOrderChildDao.updateGradTwo", record);
	}

	
	@Override
	public OrderChild selectByPrimaryKey(Integer id)
	{
		return getMasterSqlSessionUtil().selectOne(
				"com.edaisong.api.dao.inter.IOrderChildDao.selectByPrimaryKey", id);
	}	  
	
	  /**
     * 根据订单信息查询 子订单集合 
     * @param orderNo 订单号
     * @param businessId 商户id
     * @author CaoHeYang
     * @Date 20150804
     * @return
     */
	@Override
	public List<OrderChild> getOrderChildByOrderInfo(String orderNo,
			int businessId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("OrderNo", orderNo);
		paramMap.put("BusinessId", businessId);
		List<OrderChild> result=getReadOnlySqlSessionUtil().selectList("com.edaisong.api.dao.inter.IOrderChildDao.getOrderChildByOrderInfo"
				 , paramMap);
	     return result;

	}
	   /**
	    *  获取任务支付状态（0：未支付 1：部分支付 2：已支付）
	    *  @author CaoHeYang
	    * @param orderId
	    * @date  20150831
	    * @return
	    */
	@Override
	public int getOrderTaskPayStatus(int orderId) {
		Map<String ,Object> maps=new HashedMap();
		maps.put("orderId", orderId);
		int res= getReadOnlySqlSessionUtil().selectOne("com.edaisong.api.dao.inter.IOrderChildDao.getOrderTaskPayStatus", maps);
		return res;
	}
	
	
}
