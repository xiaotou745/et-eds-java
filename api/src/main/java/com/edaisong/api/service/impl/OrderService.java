package com.edaisong.api.service.impl;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.dal.dao.inter.IBusinessBalanceRecordDao;
import com.edaisong.api.dal.dao.inter.IBusinessDao;
import com.edaisong.api.dal.dao.inter.IOrderDao;
import com.edaisong.api.dal.dao.inter.IOrderOtherDao;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.core.enums.BusinessBalanceRecordRecordType;
import com.edaisong.core.enums.BusinessBalanceRecordStatus;
import com.edaisong.core.enums.OrderStatus;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.Order;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseCode;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.req.CancelOrderBusinessReq;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.OrderReq;
import com.edaisong.entity.req.PagedOrderSearchReq;
import com.edaisong.entity.resp.CancelOrderBusinessResp;
import com.edaisong.entity.resp.OrderDetailBusinessResp;
import com.edaisong.entity.resp.OrderResp;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private IOrderDao orderDao;
	@Autowired
	private IOrderOtherDao orderOtherDao;
	@Autowired
	private IBusinessDao businessDao;
	@Autowired
	private IBusinessBalanceRecordDao businessBalanceRecordDao;

	/**
	 * 后台订单列表页面
	 * 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @param search
	 *            查询条件实体
	 * @return
	 */
	@Override
	public PagedResponse<OrderListModel> getOrders(PagedOrderSearchReq search) {
		return orderDao.getOrders(search);
	}

	/**
	 * 后台订单列表页面
	 * 
	 * @author CaoHeYang
	 * @Date 20150728
	 * @param orderid
	 *            订单id
	 * @return
	 */
	@Override
	public OrderMapDetail getOrderMapDetail(long orderid) {
		return orderDao.getOrderMapDetail(orderid);
	}

	/**
	 * 商家后台 订单详情页面完整数据
	 * 
	 * @param para
	 *            查询条件
	 * @author CaoHeYang
	 * @Date 20150804
	 * @return
	 */
	@Override
	public OrderDetailBusinessResp getOrderDetailBusiness(
			OrderDetailBusinessReq para) {
		OrderDetailBusinessResp modelsBusinessResp = new OrderDetailBusinessResp();
		modelsBusinessResp.setOrderModel(orderDao.getOrderDetailBusiness(para));
		modelsBusinessResp.setOrderOthers(orderOtherDao
				.getOrderChildByOrderInfo(para.getOrderNo(),
						para.getBusinessId()));
		return modelsBusinessResp;
	}

	/**
	 * 商户取消订单功能
	 * 
	 * @param req
	 *            参数
	 * @author CaoHeYang
	 * @Date 20150804
	 * @return
	 */
	@Override
	public CancelOrderBusinessResp cancelOrderBusiness(
			CancelOrderBusinessReq req) {
		CancelOrderBusinessResp resp = new CancelOrderBusinessResp();
		if (req.getOrderId() <= 0 || req.getOrderNo().isEmpty()
				|| req.getOrderNo() == null || req.getBusinessId() <= 0) {
			resp.setResponseCode(ResponseCode.PARAMETER_FORMAT_ERROR);
			resp.setMessage("取消失败");
			return resp;
		}
		Order orderSearch = new Order();//查询取消的订单的基础数据
		orderSearch.setId(req.getOrderId());
		orderSearch.setOrderno(req.getOrderNo());
		orderSearch.setBusinessid(req.getBusinessId());
		orderSearch.setStatus((byte) OrderStatus.New.value()); // 查询状态属于待接单的
		Order orderRe = orderDao.getOneByCriteria(orderSearch); // 查询取消的订单的基础数据
		if (orderRe == null) {
			resp.setResponseCode(ResponseCode.PARAMETER_FORMAT_ERROR);
			resp.setMessage("取消订单失败,订单已被抢或订单不存在！");
			return resp;
		}
        cancelOrderBusinessTrans(req,orderRe);  //事务代码
        resp.setResponseCode(ResponseCode.SUCESS);
		resp.setMessage("取消订单成功！");
		return resp;
	}
	
	/**
	 * 商户取消订单功能事务单元
	 * @author CaoHeYang
	 * @param req  请求参数
	 * @param orderRe  当前要取消的订单的基础数据
	 * @Date 20150806
	 */
	@Transactional(rollbackFor = Exception.class,timeout=30)
	public void cancelOrderBusinessTrans(CancelOrderBusinessReq req,Order orderRe) {
		// 更新订单为 取消状态 参数
		Order updateModel = new Order();
		updateModel.setId(req.getOrderId());
		updateModel.setOrderno(req.getOrderNo());
		updateModel.setBusinessid(req.getBusinessId());
		updateModel.setStatus((byte) OrderStatus.New.value()); // 查询状态属于待接单的
		updateModel.setOthercancelreason("商家取消订单");
		updateModel.setAmount(orderRe.getSettlemoney()); // 取消订单涉及到的金额数目此处取到的当前订单的 结算费 即商家应付

		if (orderDao.cancelOrderBusiness(updateModel) > 0 /*取消订单 针对订单表的逻辑*/ 
				&& businessDao.updateForWithdraw(orderRe.getSettlemoney(),req.getBusinessId())>0  /*更新商户余额*/ ){ // 取消成功
			BusinessBalanceRecord businessBalanceRecord = new BusinessBalanceRecord();
			businessBalanceRecord.setBusinessid(req.getBusinessId());// 商户Id
			businessBalanceRecord.setAmount(orderRe.getSettlemoney());
			businessBalanceRecord.setStatus((short)BusinessBalanceRecordStatus.Success.value()); // 流水状态(1、交易成功 2、交易中）
			businessBalanceRecord.setRecordtype((short)BusinessBalanceRecordRecordType.CancelOrder.value()); // 取消订单
			businessBalanceRecord.setOperator("商家:" + req.getBusinessId());  //商家id
			businessBalanceRecord.setWithwardid((long) req.getOrderId()); //订单id
			businessBalanceRecord.setRelationno(req.getOrderNo()); //关联单号
			businessBalanceRecord.setRemark("商户取消订单返回配送费");  //注释
		    businessBalanceRecordDao.insert(businessBalanceRecord);  //记录
		}else {
			throw new RuntimeException("更新订单状态为取消失败");
		}
	}
	
	/**
	 * 商户发布订单功能
	 * 
	 * @param req
	 *            参数
	 * @author 胡灵波
	 * @Date 2015年8月6日 09:56:25
	 * @return
	 */
	public OrderResp AddOrder(OrderReq  req)
	{


//        dbParameters.AddWithValue("@SongCanDate", order.SongCanDate);		
//        dbParameters.AddWithValue("@Weight1", order.Weight);
//        dbParameters.AddWithValue("@Quantity1", order.Quantity);		
//        dbParameters.AddWithValue("@ReceiveProvince", order.ReceiveProvince);
//        dbParameters.AddWithValue("@ReceiveProvinceCode", order.ReceiveProvinceCode);
		
//        dbParameters.AddWithValue("@ReceiveCityCode", order.ReceiveCityCode);
//        dbParameters.AddWithValue("@ReceiveArea", order.ReceiveArea);
//        dbParameters.AddWithValue("@ReceiveAreaCode", order.ReceiveAreaCode);
//        dbParameters.AddWithValue("@OriginalOrderNo", order.OriginalOrderNo);
  
      

		OrderResp resp=new OrderResp();
		
		//订单主表
		Order order=new Order();		
		order.setOrderno("no11111111");//临时
		//获取商家信息
		order.setPickupaddress("北京市朝阳区东坝乡朝新嘉园东里五区18号楼");//临时 通过商家获取
		order.setBusinessid(2053);
		order.setRecevicecity("北京市");
		order.setCommissionformulamode(0);		
		order.setBusinesscommission(BigDecimal.valueOf(10));
		order.setBusinessgroupid(1);
		order.setCommissiontype(1);
		order.setCommissionfixvalue(BigDecimal.valueOf(0));
		order.setMealssettlemode(0);	
		 
		order.setRecevicename("测试");
		order.setRecevicephoneno("18301222651");
		order.setReceviceaddress("北京市朝阳区百子湾11");
		order.setIspay(true);		
		order.setAmount(BigDecimal.valueOf(193.00));
		order.setRemark("配送说明");
		order.setOrderfrom(0);//订单来源
		order.setStatus(Byte.parseByte("0"));
		order.setRecevicelongitude(0.0);
		order.setRecevicelatitude(0.0);
		order.setOrdercount(1);
		order.setTimespan("1");		
		
		order.setOrdercommission(BigDecimal.valueOf(21.30));
		order.setDistribsubsidy(BigDecimal.valueOf(0));		
		order.setWebsitesubsidy(BigDecimal.valueOf(2));
		order.setCommissionrate(BigDecimal.valueOf(0.1));
		order.setSettlemoney(BigDecimal.valueOf(19.30));	   
		order.setAdjustment(BigDecimal.valueOf(0));		
		order.setBusinessreceivable(BigDecimal.valueOf(0));//退还商家金额	
		
		orderDao.insert(order);		
		
		return resp;
	}	
	
}

