package com.edaisong.api.service.impl;

import javax.ws.rs.core.NewCookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dal.dao.inter.IBusinessBalanceRecordDao;
import com.edaisong.api.dal.dao.inter.IBusinessDao;
import com.edaisong.api.dal.dao.inter.IOrderChildDao;
import com.edaisong.api.dal.dao.inter.IOrderDao;
import com.edaisong.api.dal.dao.inter.IOrderOtherDao;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.Order;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseCode;
import com.edaisong.entity.domain.OrderListModel;
import com.edaisong.entity.domain.OrderMapDetail;
import com.edaisong.entity.req.CancelOrderBusinessReq;
import com.edaisong.entity.req.OrderDetailBusinessReq;
import com.edaisong.entity.req.PagedOrderSearchReq;
import com.edaisong.entity.resp.CancelOrderBusinessResp;
import com.edaisong.entity.resp.OrderDetailBusinessResp;

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
		CancelOrderBusinessResp resp=new CancelOrderBusinessResp();
		Order orderRe=new Order();
		boolean result=checkCancelOrderBusiness(req,orderRe);
		if (!result) {
			resp.setResponseCode(ResponseCode.PARAMETER_FORMAT_ERROR);
		}
		
		//更新订单为 取消状态 参数 
		Order updateModel=new Order();
		updateModel.setId(req.getOrderId());
		updateModel.setOrderno(req.getOrderNo());
		updateModel.setBusinessid(req.getBusinessId());
		updateModel.setStatus((byte)0);    //查询状态属于待接单的   
		updateModel.setOthercancelreason("商家取消订单");
		updateModel.setAmount(orderRe.getSettlemoney());  //取消订单涉及到的金额数目   此处取到的当前订单的 结算费 即 商家应付
		
		result =orderDao.cancelOrderBusiness(updateModel)>0;  //取消订单 针对订单表的逻辑
		
		
		if (result) {  //取消成功
			businessDao.updateForWithdraw(orderRe.getSettlemoney(), req.getBusinessId());  //orderRe.getSettlemoney()
			
			BusinessBalanceRecord businessBalanceRecord=new BusinessBalanceRecord();
			businessBalanceRecord.setBusinessid(req.getBusinessId());//商户Id
			businessBalanceRecord.setAmount(orderRe.getSettlemoney());
			businessBalanceRecord.setStatus((short) 1);  //(int)BusinessBalanceRecordStatus.Success, //流水状态(1、交易成功 2、交易中）
			businessBalanceRecord.setRecordtype((short)2); //取消订单
			businessBalanceRecord.setOperator("商家:"+req.getBusinessId());
			businessBalanceRecord.setWithwardid((long)req.getOrderId());
			businessBalanceRecord.setRelationno(req.getOrderNo());
			businessBalanceRecord.setRemark("商户取消订单返回配送费");
			businessBalanceRecordDao.insert(businessBalanceRecord);
			
//			businessBalanceRecord BusinessId = paramodel.BusinessId,//商户Id
//          Amount = order.SettleMoney,//流水金额  结算金额
//          Status = (int)BusinessBalanceRecordStatus.Success, //流水状态(1、交易成功 2、交易中）
//          RecordType = (int)BusinessBalanceRecordRecordType.CancelOrder,
//          Operator = string.Format("商家:{0}", paramodel.BusinessId),
//          WithwardId = paramodel.OrderId,
//          RelationNo = paramodel.OrderNo,
//          Remark = "商户取消订单返回配送费"
		}
		
		return resp;
	}


	/**
	 * 商户取消订单功能  验证参数合法性，已经当前订单是否允许取消 
	 * @param para
	 * @param orderRe out 参数
	 * @author CaoHeYang
	 * @Date 20150804
	 * @return
	 */
    private boolean checkCancelOrderBusiness(CancelOrderBusinessReq para, Order orderRe)
    {
        if (para.getOrderId() <= 0 ||para.getOrderNo().isEmpty()||para.getOrderNo()==null||para.getBusinessId()<=0)
        {
            return false;
        }
        
        //查询条件
        Order orderSearch=new Order();
        orderSearch.setId(para.getOrderId());
        orderSearch.setOrderno(para.getOrderNo());
        orderSearch.setBusinessid(orderSearch.getBusinessid());
        orderSearch.setStatus((byte)0);    //查询状态属于待接单的   
        
        orderRe = orderDao.getOneByCriteria(orderSearch);
        if (orderRe == null)
        {
            return false;
        }
        return false;
    }
}
